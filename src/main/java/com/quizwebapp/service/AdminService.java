package com.quizwebapp.service;

import com.quizwebapp.dao.AdminDao;
import com.quizwebapp.dao.QuizDao;
import com.quizwebapp.dao.UserDao;
import com.quizwebapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AdminService {
    AdminDao adminDao;
    QuizDao quizDao;
    UserDao userDao;
    QuizService quizService;

    @Autowired
    public AdminService(AdminDao adminDao, QuizDao quizDao,
                        UserDao userDao, QuizService quizService) {
        this.adminDao = adminDao;
        this.quizDao = quizDao;
        this.userDao = userDao;
        this.quizService = quizService;
    }

    public void addContact(String subject, String email, String message) {
        adminDao.addContact(subject, email, message);
    }

    public List<Contact> getAllContact() {
        return adminDao.getAllContact();
    }

    public List<User> getAllUsers() {
        return adminDao.getAllUser();
    }

    public void suspendUser(int userId) {
        adminDao.changeUserStatus(userId, false);
    }

    public void activateUser(int userId) {
        adminDao.changeUserStatus(userId, true);
    }

    public List<QuizResult> getAllQuizResult() {
        List<Quiz> allQuiz = adminDao.getAllQuiz();

        return getQuizResultFromQuiz(allQuiz);
    }

    public List<QuizResult> getQuizResultFromQuiz(List<Quiz> quizList) {
        List<QuizResult> resList = new LinkedList<>();

        for (Quiz quiz : quizList) {
            QuizResult qr = new QuizResult();
            qr.setUserName(userDao.getUserById(quiz.getUserId()).getName());
            qr.setCategory(quizDao.getCategoryById(quiz.getCategoryId()).getName());
            qr.setTakenTime(quiz.getTimeEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            qr.setQuestionListStr(this.convertIdsToString(quizDao.getQuestionIdListByQuizId(quiz.getId())));
            qr.setScore(quizService.getQuizScore(quiz));
            qr.setQuizId(quiz.getId());

            resList.add(qr);
        }

        resList.sort((o1, o2) -> o2.getTakenTime().compareTo(o1.getTakenTime()));

        return resList;
    }

    public List<Category> getAllCategories() {
        return adminDao.getAllCategories();
    }

    private String convertIdsToString(List<Integer> ids) {
        StringBuilder sb = new StringBuilder();

        for (int id : ids) {
            sb.append(id);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.toString();
    }

    public List<QuizResult> getQuizResultByFilter(String categoryId, String userId) {
        List<Quiz> filteredQuiz = null;

        if (!Objects.equals(categoryId, "") && Objects.equals(userId, "")) {
            filteredQuiz = adminDao.getQuizByCategoryId(Integer.parseInt(categoryId));
        } else if (Objects.equals(categoryId, "") && !Objects.equals(userId, "")) {
            filteredQuiz = adminDao.getQuizByUserId(Integer.parseInt(userId));
        } else if (!Objects.equals(categoryId, "") && !Objects.equals(userId, "")){
            filteredQuiz =
                    adminDao.getQuizByCategoryIdAndUserId(Integer.parseInt(categoryId), Integer.parseInt(userId));
        } else {
            filteredQuiz = adminDao.getAllQuiz();
        }

        return getQuizResultFromQuiz(filteredQuiz);
    }

    public List<Question> getAllQuestion() {
        return adminDao.getAllQuestion();
    }
}
