package com.quizwebapp.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ResponseStatus {
    private boolean success;
    private String message;
}
