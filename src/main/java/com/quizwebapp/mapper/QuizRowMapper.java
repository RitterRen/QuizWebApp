package com.quizwebapp.mapper;

import com.quizwebapp.entity.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {

    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setId(rs.getInt("quiz_id"));
        quiz.setUserId(rs.getInt("user_id"));
        quiz.setCategoryId(rs.getInt("category_id"));
        quiz.setQuizName(rs.getString("quiz_name"));

        Timestamp timeStartTimestamp = rs.getTimestamp("time_start");
        LocalDateTime timeStart = timeStartTimestamp != null ? timeStartTimestamp.toLocalDateTime() : null;
        quiz.setTimeStart(timeStart);

        Timestamp timeEndTimestamp = rs.getTimestamp("time_end");
        LocalDateTime timeEnd = timeEndTimestamp != null ? timeEndTimestamp.toLocalDateTime() : null;
        quiz.setTimeEnd(timeEnd);

        return quiz;
    }
}
