package com.quizwebapp.entity;

public class QuizQuestion {
    private int id;
    private int quizId;
    private int questionId;
    private int userChoiceId;

    public QuizQuestion() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserChoiceId() {
        return userChoiceId;
    }

    public void setUserChoiceId(int userChoiceId) {
        this.userChoiceId = userChoiceId;
    }
}
