package com.quizwebapp.controller;

import com.quizwebapp.entity.Quiz;
import com.quizwebapp.entity.User;
import com.quizwebapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request) {
        // Do not create new session if there is no session exist
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login-check")
    public String postLogin(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        Optional<User> possibleUser = userService.validateLogin(email, password);

        if (possibleUser.isPresent()) {
            // Create and set new session
            HttpSession oldSession = request.getSession();
            if (oldSession != null) oldSession.invalidate();
            HttpSession newSession = request.getSession(true);
            User user = possibleUser.get();
            newSession.setAttribute("user", user);
            Quiz currentQuiz = userService.hasProcessingQuiz(user);
            if (currentQuiz != null) {
                newSession.setAttribute("quiz", currentQuiz);
                return "redirect:/quiz";
            }

            return "redirect:/home";
        }

        model.addAttribute("loginError", "Password entered not correct, please enter again");
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) oldSession.invalidate();

        return "login";
    }
}
