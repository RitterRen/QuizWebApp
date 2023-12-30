package com.quizwebapp.controller;

import com.quizwebapp.dao.AdminDao;
import com.quizwebapp.dao.QuizDao;
import com.quizwebapp.dao.UserDao;
import com.quizwebapp.dto.common.ResponseStatus;
import com.quizwebapp.dto.quiz.QuizListResponse;
import com.quizwebapp.dto.user.UserListResponse;
import com.quizwebapp.dto.user.UserResponse;
import com.quizwebapp.entity.Quiz;
import com.quizwebapp.entity.User;
import com.quizwebapp.service.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class RestUserController {
    UserService userService;
    UserDao userDao;
    AdminDao adminDao;
    QuizDao quizDao;

    @Autowired
    public RestUserController(UserService userService, UserDao userDao, AdminDao adminDao, QuizDao quizDao) {
        this.userService = userService;
        this.userDao = userDao;
        this.adminDao = adminDao;
        this.quizDao = quizDao;
    }

    @PostMapping()
    public UserResponse addUser(@RequestParam @NotBlank String email,
                                @RequestParam @NotBlank String firstName,
                                @RequestParam @NotBlank String lastName,
                                @RequestParam @NotBlank String password) {
        boolean success = userService.createUser(firstName, lastName, email, password);

        if (success) {
            return UserResponse.builder()
                    .responseStatus(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("User create successfully!")
                            .build()
                    )
                    .user(userDao.getUserByEmail(email))
                    .build();
        }

        return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(false)
                                .message("User create failed")
                                .build()
                )
                .user(null)
                .build();
    }

    @PostMapping("/addUserWithObject")
    public UserResponse addUser(@RequestBody @NotNull User user) {
        boolean success = userService.createUser(user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword());

        if (success) {
            return UserResponse.builder()
                    .responseStatus(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("User create successfully!")
                                    .build()
                    )
                    .user(userDao.getUserByEmail(user.getEmail()))
                    .build();
        }

        return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(false)
                                .message("User create failed")
                                .build()
                )
                .user(null)
                .build();
    }

    @DeleteMapping("/{userId}")
    public UserResponse deleteUser(@PathVariable @Min(value = 0) int userId) {
        User user = userDao.getUserById(userId);
        boolean success = userDao.deleteUserById(userId);

        if (success) return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Delete user with Id : " + userId + " successfully!")
                                .build())
                .user(user)
                .build();

        return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(false)
                                .message("Delete user " + userId + " failed.")
                                .build()
                )
                .user(null)
                .build();
    }

    @PatchMapping("/{userId}/status")
    public UserResponse updateUserStatus(@PathVariable @Min(value = 0) int userId, @RequestParam boolean activate) {
        boolean success = adminDao.changeUserStatus(userId, activate);

        if (success) return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Update success with user " + userId + " with activate : " + activate)
                                .build()
                )
                .user(userDao.getUserById(userId))
                .build();

        return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(false)
                                .message("Failed to change user " + userId + " status")
                                .build()
                )
                .build();
    }

    @GetMapping()
    public UserResponse getUser(@RequestParam int userId) {
        User user = userDao.getUserById(userId);

        if (user == null) return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(false)
                                .message("Get user failed with userId " + userId)
                                .build()
                )
                .build();

        return UserResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Get user successfully !")
                                .build()
                )
                .user(user)
                .build();
    }

    @GetMapping("/all")
    public UserListResponse getAllUser() {
        List<User> allUser = adminDao.getAllUser();

        if (allUser == null) return UserListResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(false)
                                .message("Get all user failed")
                                .build()
                )
                .build();

        return UserListResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Get all user successfully")
                                .build()
                )
                .userList(allUser)
                .build();
    }

    @GetMapping("/quiz/{userId}")
    public QuizListResponse getQuizByUserId(@PathVariable @Min(value = 0) int userId) {
        List<Quiz> quizList = quizDao.getAllQuizByUserId(userId);

        if (quizList == null) return QuizListResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(false)
                                .message("Failed to get all the quiz from user " + userId)
                                .build()
                )
                .build();

        return QuizListResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Get all the quiz successfully with userId : " + userId)
                                .build()
                )
                .quizList(quizList)
                .build();
    }

}
