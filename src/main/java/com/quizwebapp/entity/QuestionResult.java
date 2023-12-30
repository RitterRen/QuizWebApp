package com.quizwebapp.entity;

import java.util.List;

public class QuestionResult {
    private Question question;
    private List<Choice> questionOptions;
    private Choice userSelectedOption;
    private Choice correctOption;

    public QuestionResult() {}

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Choice> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<Choice> questionOptions) {
        this.questionOptions = questionOptions;
    }

    public Choice getUserSelectedOption() {
        return userSelectedOption;
    }

    public void setUserSelectedOption(Choice userSelectedOption) {
        this.userSelectedOption = userSelectedOption;
    }

    public Choice getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Choice correctOption) {
        this.correctOption = correctOption;
    }
}
