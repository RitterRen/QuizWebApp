package com.quizwebapp.service;

import com.quizwebapp.dao.QuizDao;
import com.quizwebapp.dao.UserDao;
import com.quizwebapp.entity.Quiz;
import com.quizwebapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserDao userDao;
    QuizDao quizDao;
    @Autowired
    public UserService(UserDao userDao, QuizDao quizDao) {
        this.userDao = userDao;
        this.quizDao = quizDao;
    }

    public boolean createUser(String firstName, String lastName,
                              String email, String password) {
        User user = new User(firstName, lastName, email, password);
        System.out.println(user);

        return userDao.createUser(user);
    }

    public Optional<User> validateLogin(String email, String plainPassword) {
        User user = userDao.getUserByEmail(email);
        if (user == null) return Optional.empty();

        return User.checkPassword(plainPassword, user.getPassword()) ? Optional.ofNullable(user) : Optional.empty();
    }

    public Quiz hasProcessingQuiz(User user) {
        return quizDao.getProcessingQuiz(user.getId());
    }
}
