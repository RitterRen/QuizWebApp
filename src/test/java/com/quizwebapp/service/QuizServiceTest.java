package com.quizwebapp.service;

import com.quizwebapp.entity.Question;
import com.quizwebapp.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuizServiceTest {

    @Autowired
    private QuizService quizService;

    @Test
    public void testGetAllCategory() {
        List<Category> categoryList = quizService.getAllCategory();

        assertNotNull(categoryList);
        assertFalse(categoryList.isEmpty());
    }

    @Test
    public void testGetFiveRandomQuizByCategoryId() {
        List<Question> questionList = quizService.getFiveRandomQuizByCategoryId(1);

        System.out.println(questionList);
        assertEquals(5, questionList.size());
    }

}
