package com.quizwebapp.dao;

import com.quizwebapp.entity.*;
import com.quizwebapp.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    ContactRowMapper contactRowMapper;
    UserRowMapper userRowMapper;
    QuizRowMapper quizRowMapper;
    CategoryRowMapper categoryRowMapper;
    QuestionRowMapper questionRowMapper;
    @Autowired
    public AdminDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                    ContactRowMapper contactRowMapper, UserRowMapper userRowMapper,
                    QuizRowMapper quizRowMapper, CategoryRowMapper categoryRowMapper,
                    QuestionRowMapper questionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.contactRowMapper = contactRowMapper;
        this.userRowMapper = userRowMapper;
        this.quizRowMapper = quizRowMapper;
        this.categoryRowMapper = categoryRowMapper;
        this.questionRowMapper = questionRowMapper;
    }

    public void addContact(String subject, String email, String message) {
        String sql = "INSERT INTO Contact (contact_subject, email, message) VALUES (:subject, :email, :message)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("subject", subject);
        parameters.addValue("email", email);
        parameters.addValue("message", message);

        namedParameterJdbcTemplate.update(sql, parameters);
    }

    public List<Contact> getAllContact() {
        String sql = "SELECT * FROM Contact";

        return namedParameterJdbcTemplate.query(sql, contactRowMapper);
    }

    public List<User> getAllUser() {
        String sql = "SELECT * FROM User";

        return namedParameterJdbcTemplate.query(sql, userRowMapper);
    }

    public boolean changeUserStatus(int userId, boolean status) {
        String sql = "UPDATE User SET is_active = :isActive WHERE user_id = :userId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("isActive", status);
        parameters.addValue("userId", userId);

        return namedParameterJdbcTemplate.update(sql, parameters) > 0;
    }

    public List<Quiz> getAllQuiz() {
        String sql = "SELECT * FROM Quiz WHERE time_end IS NOT NULL";

        return namedParameterJdbcTemplate.query(sql, quizRowMapper);
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Category";

        return namedParameterJdbcTemplate.query(sql, categoryRowMapper);
    }

    public List<Quiz> getQuizByCategoryId(int categoryId) {
        String sql = "SELECT * FROM Quiz WHERE category_id = :categoryId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("categoryId", categoryId);

        return namedParameterJdbcTemplate.query(sql, parameters, quizRowMapper);
    }

    public List<Quiz> getQuizByUserId(int userId) {
        String sql = "SELECT * FROM Quiz WHERE user_id = :userId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userId", userId);

        return namedParameterJdbcTemplate.query(sql, parameters, quizRowMapper);
    }

    public List<Quiz> getQuizByCategoryIdAndUserId(int categoryId, int userId) {
        String sql = "SELECT * FROM Quiz WHERE user_id = :userId AND category_id = :categoryId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userId", userId);
        parameters.addValue("categoryId", categoryId);

        return namedParameterJdbcTemplate.query(sql, parameters, quizRowMapper);
    }

    public List<Question> getAllQuestion() {
        String sql = "SELECT * FROM Question";

        return namedParameterJdbcTemplate.query(sql, questionRowMapper);
    }
}
