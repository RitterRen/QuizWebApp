package com.quizwebapp.service;

import com.google.protobuf.MapEntry;
import com.quizwebapp.entity.*;
import com.quizwebapp.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class QuizService {
    QuizDao quizDao;
    @Autowired
    public QuizService(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public List<Category> getAllCategory() {
        List<Category> categoryList = quizDao.getAllCategory();

        return categoryList != null ? categoryList : new ArrayList<>();
    }

    public Category getCategoryById(int id) {
        return quizDao.getCategoryById(id);
    }

    public List<Question> getFiveRandomQuizByCategoryId(int categoryId) {
        return quizDao.getFiveRandomQuizByCategoryId(categoryId);
    }

    public HashMap<Integer, List<Choice>> getChoiceQuestionMap(List<Question> questionList) {
        HashMap<Integer, List<Choice>> choiceQuestionMap = new HashMap<>();

        for (Question question: questionList) {
            choiceQuestionMap.put(question.getId(), quizDao.getChoiceByQuestionId(question.getId()));
        }

        return choiceQuestionMap;
    }

    @Transactional
    public Quiz createQuiz(User user, int categoryId, Collection<Question> questionList) {
        Category category = quizDao.getCategoryById(categoryId);
        String quizName = user.getName() + " " + category.getName() + "'s" + " Quiz";
        LocalDateTime currentTime = LocalDateTime.now();

        Quiz quiz = quizDao.createQuiz(user.getId(), category.getId(), quizName, Timestamp.valueOf(currentTime));
        this.addQuizQuestionWithoutUserChoice(quiz, questionList);

        return quiz;
    }

    @Transactional
    public void processQuizAnswerMap(Map<String, String> answerMap, Quiz quiz) {
        for (Map.Entry<String, String> entry : answerMap.entrySet()) {
            int questionId = Integer.parseInt(entry.getKey());
            int userChoiceId = Integer.parseInt(entry.getValue());

            quizDao.updateQuestionWithUserChoice(quiz.getId(), questionId, userChoiceId);
        }

        quizDao.updateQuizEndTimeWithQuizId(quiz.getId(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public void addQuizQuestionWithoutUserChoice(Quiz quiz, Collection<Question> questionList) {
        for (Question question : questionList) {
            quizDao.addQuizQuestionWithoutUserChoice(quiz.getId(), question.getId());
        }
    }

    public Map<Question, List<Choice>> getQuestionChoiceByQuizId(Quiz quiz) {
        List<Integer> questionIdList = quizDao.getQuestionIdListByQuizId(quiz.getId());
        Map<Question, List<Choice>> questionChoiceMap = new HashMap<>();

        for (int questionId : questionIdList) {
            Question question = quizDao.getQuestionById(questionId);
            if (question != null) questionChoiceMap.put(question, quizDao.getChoiceByQuestionId(questionId));
        }

        return questionChoiceMap;
    }

    public Map<Question, List<Choice>> getQuestionChoiceByCategoryId(int categoryId) {
        List<Question> questionList = this.getFiveRandomQuizByCategoryId(categoryId);
        Map<Question, List<Choice>> questionChoiceMap = new HashMap<>();

        for (Question question : questionList) {
            questionChoiceMap.put(question, quizDao.getChoiceByQuestionId(question.getId()));
        }

        return questionChoiceMap;
    }

    public boolean getQuizResult(Quiz quiz) {

        return this.getQuizScore(quiz) >= 3;
    }

    public int getQuizScore(Quiz quiz) {
        List<Integer> questionIdList = quizDao.getQuestionIdListByQuizId(quiz.getId());
        int correctCount = 0;

        for (int questionId : questionIdList) {
            Choice correctChoice = quizDao.getCorrectAnswerWithQuestionId(questionId);
            int userChoiceId = quizDao.getUserChoiceIdByQuizIdAndQuestionId(quiz.getId(), questionId);
            Choice userChoice = quizDao.getChoiceById(userChoiceId);

            if (userChoice.getId() == correctChoice.getId()) correctCount++;
        }

        return correctCount;
    }

    public List<QuestionResult> getQuestionResultList(Quiz quiz) {
        List<Integer> questionIdList = quizDao.getQuestionIdListByQuizId(quiz.getId());
        List<QuestionResult> qrList = new LinkedList<>();

        for (int questionId : questionIdList) {
            QuestionResult qr = new QuestionResult();
            qr.setQuestion(quizDao.getQuestionById(questionId));
            qr.setQuestionOptions(quizDao.getChoiceByQuestionId(questionId));
            int userChoiceId = quizDao.getUserChoiceIdByQuizIdAndQuestionId(quiz.getId(), questionId);
            qr.setUserSelectedOption(quizDao.getChoiceById(userChoiceId));
            qr.setCorrectOption(quizDao.getCorrectAnswerWithQuestionId(questionId));

            qrList.add(qr);
        }

        return qrList;
    }

    public List<RecentQuizResult> getRecentQuizResultList(User user) {
        List<Quiz> recentQuizList = quizDao.getAllQuizByUserId(user.getId());
        List<RecentQuizResult> res = new LinkedList<>();

        for (Quiz quiz : recentQuizList) {
            RecentQuizResult rqRes = new RecentQuizResult();
            rqRes.setName(quiz.getQuizName());
            rqRes.setDateTaken(quiz.getTimeEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            rqRes.setQuizId(quiz.getId());

            res.add(rqRes);
        }

        return res;
    }
}
