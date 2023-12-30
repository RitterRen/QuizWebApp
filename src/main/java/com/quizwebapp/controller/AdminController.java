package com.quizwebapp.controller;

import com.quizwebapp.entity.*;
import com.quizwebapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class AdminController {
    AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String getAdmin() {

        return "admin/admin-home";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact-us";
    }

    @PostMapping("/contact")
    public String postContact(@RequestParam String subject, @RequestParam String email,
                              @RequestParam String message) {

        adminService.addContact(subject, email, message);

        return "contact-us";
    }

    @GetMapping("/contactManagement")
    public String getContactList(Model model) {
        List<Contact> contactList = adminService.getAllContact();
        model.addAttribute("contactList", contactList);

        return "admin/contact-management";
    }

    @GetMapping("/userManagement")
    public String getUserManagement(Model model) {
        List<User> userList = adminService.getAllUsers();
        model.addAttribute("userList", userList);

        return "admin/user-management";
    }

    @PostMapping("/suspendUser/{userId}")
    public String suspendUser(@PathVariable("userId") int userId) {
        adminService.suspendUser(userId);

        return "redirect:/userManagement";
    }

    @PostMapping("/activateUser/{userId}")
    public String activateUser(@PathVariable("userId") int userId) {
        adminService.activateUser(userId);

        return "redirect:/userManagement";
    }

    @GetMapping("/quizResultManagement")
    public String getQuizResultManagement(Model model) {
        List<QuizResult> quizResultList = adminService.getAllQuizResult();
        model.addAttribute("quizResultList", quizResultList);
        this.setCategoryAndUserToModel(model);

        return "admin/quiz-management";
    }

    @GetMapping("/filterQuizResult")
    public String filterQuizResult(@RequestParam String categoryId, @RequestParam String userId, Model model) {
        List<QuizResult> quizResultList = adminService.getQuizResultByFilter(categoryId, userId);
        model.addAttribute("quizResultList", quizResultList);
        this.setCategoryAndUserToModel(model);

        return "admin/quiz-management";
    }

    @GetMapping("/questionManagement")
    public String questionManagement(Model model) {
        List<Question> allQuestion = adminService.getAllQuestion();
        model.addAttribute("questionList", allQuestion);
        HashMap<Integer, String> categoryMap = new HashMap<>();
        categoryMap.put(1, "Math");
        categoryMap.put(2, "Movie");
        categoryMap.put(3, "Sports");
        model.addAttribute("categoryMap", categoryMap);

        return "admin/question-management";
    }

    private void setCategoryAndUserToModel(Model model) {
        List<Category> categoryList = adminService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        List<User> userList = adminService.getAllUsers();
        model.addAttribute("userList", userList);
    }
}
