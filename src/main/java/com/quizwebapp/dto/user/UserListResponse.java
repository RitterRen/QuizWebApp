package com.quizwebapp.dto.user;

import com.quizwebapp.dto.common.ResponseStatus;
import com.quizwebapp.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class UserListResponse {
    private List<User> userList;
    private ResponseStatus status;
}
