package com.quizwebapp.mapper;

import com.quizwebapp.entity.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ChoiceRowMapper implements RowMapper<Choice> {

    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();
        choice.setQuestionId(rs.getInt("question_id"));
        choice.setDescription(rs.getString("description"));
        choice.setCorrect(rs.getBoolean("is_correct"));
        choice.setId(rs.getInt("choice_id"));

        return choice;
    }
}
