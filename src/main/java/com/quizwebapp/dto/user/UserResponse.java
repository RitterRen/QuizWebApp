package com.quizwebapp.dto.user;

import com.quizwebapp.dto.common.ResponseStatus;
import com.quizwebapp.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    ResponseStatus responseStatus;
    User user;
}
