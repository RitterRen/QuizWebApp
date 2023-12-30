package com.quizwebapp.dao;

import com.quizwebapp.entity.Choice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuizDaoTest {

    @Autowired
    QuizDao quizDao;

    @Test
    public void testGetChoiceByQuestionId() {
        List<Choice> choiceList = quizDao.getChoiceByQuestionId(1);

        System.out.println(choiceList);
        assertEquals(choiceList.size(), 4);
    }

    @Test
    public void testGetCorrectAnswerWithQuestionId() {
        List<Integer> correctAnswer = quizDao.getCorrectAnswerWithQuestionId(2);

        System.out.println(correctAnswer);
        assertEquals(correctAnswer.size(), 1);
    }
}
