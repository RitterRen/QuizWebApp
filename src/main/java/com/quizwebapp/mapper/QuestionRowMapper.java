package com.quizwebapp.mapper;

import com.quizwebapp.entity.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setCategoryId(rs.getInt("category_id"));
        question.setDescription(rs.getString("description"));
        question.setActive(rs.getBoolean("is_active"));
        question.setId(rs.getInt("question_id"));

        return question;
    }
}
