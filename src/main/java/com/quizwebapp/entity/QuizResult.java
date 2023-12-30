package com.quizwebapp.entity;

public class QuizResult {
    String takenTime;
    String category;
    String userName;
    String questionListStr;
    int score;
    int quizId;

    public QuizResult() {}

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(String takenTime) {
        this.takenTime = takenTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestionListStr() {
        return questionListStr;
    }

    public void setQuestionListStr(String questionListStr) {
        this.questionListStr = questionListStr;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
