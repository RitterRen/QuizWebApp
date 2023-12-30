package com.quizwebapp.controller;

import com.quizwebapp.dao.QuizDao;
import com.quizwebapp.dao.UserDao;
import com.quizwebapp.entity.*;
import com.quizwebapp.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {
    QuizService quizService;
    QuizDao quizDao;
    UserDao userDao;

    @Autowired
    public QuizController(QuizService quizService, QuizDao quizDao, UserDao userDao) {
        this.quizService = quizService;
        this.quizDao = quizDao;
        this.userDao = userDao;
    }

    @GetMapping("/home")
    public String getHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Category> categoryList = quizService.getAllCategory();
        model.addAttribute("categoryList", categoryList);
        List<RecentQuizResult> recentQuizResultList = quizService.getRecentQuizResultList(user);
        model.addAttribute("recentQuizResultList", recentQuizResultList);

        return "home";
    }

    @GetMapping("/quiz/{category}")
    public String getQuiz(@PathVariable("category") int categoryId, Model model, HttpServletRequest request) {
        Map<Question, List<Choice>> questionChoiceMap = quizService.getQuestionChoiceByCategoryId(categoryId);
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) return "login";
        User user = (User) session.getAttribute("user");
        Quiz quiz = quizService.createQuiz(user, categoryId, questionChoiceMap.keySet());

        session.setAttribute("quiz", quiz);
        model.addAttribute("quizName", quiz.getQuizName());
        model.addAttribute("questionChoiceMap", questionChoiceMap);

        return "quiz";
    }

    @GetMapping("/quiz")
    public String getQuiz(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("quiz") != null) {
            Quiz quiz = (Quiz) session.getAttribute("quiz");
            Map<Question, List<Choice>> questionChoiceMap = quizService.getQuestionChoiceByQuizId(quiz);

            model.addAttribute("questionChoiceMap", questionChoiceMap);
            model.addAttribute("quizName", quiz.getQuizName());

            return "quiz";
        }

        return "home";
    }

    @PostMapping("/submitQuiz")
    public String submitQuiz(@RequestParam Map<String, String> answerMap, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("quiz") == null) return "login";

        Quiz quiz = (Quiz) session.getAttribute("quiz");
        quizService.processQuizAnswerMap(answerMap, quiz);
        session.removeAttribute("quiz");

        return "redirect:/quizResult/" + quiz.getId();
    }

    @GetMapping("/quizResult/{quizId}")
    public String getQuizResult(@PathVariable("quizId") int quizId, Model model, HttpServletRequest request) {
        Quiz quiz = quizDao.getQuizById(quizId);
        User user = userDao.getUserById(quiz.getUserId());

        model.addAttribute("quizName", quiz.getQuizName());
        model.addAttribute("userName", user.getName());
        model.addAttribute("quizStartTime", quiz.getTimeStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("quizEndTime", quiz.getTimeEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String testResult = quizService.getQuizResult(quiz) ? "PASS" : "FAILED";
        model.addAttribute("testResult", testResult);

        List<QuestionResult> questionResultList = quizService.getQuestionResultList(quiz);
        model.addAttribute("questionResultList", questionResultList);

        return "quiz-result";
    }

}
