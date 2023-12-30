package com.quizwebapp.dto.quiz;

import com.quizwebapp.dto.common.ResponseStatus;
import com.quizwebapp.entity.Quiz;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class QuizListResponse {
    private List<Quiz> quizList;
    private ResponseStatus status;
}
