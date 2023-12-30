package com.quizwebapp.entity;

public class Choice {
    private int questionId;
    private String description;
    private boolean isCorrect;
    private int id;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "questionId=" + questionId +
                ", description='" + description + '\'' +
                ", isCorrect=" + isCorrect +
                ", id=" + id +
                '}';
    }
}
