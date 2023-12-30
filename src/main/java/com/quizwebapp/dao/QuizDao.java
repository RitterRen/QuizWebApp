package com.quizwebapp.dao;

import com.quizwebapp.entity.Category;
import com.quizwebapp.entity.Choice;
import com.quizwebapp.entity.Question;
import com.quizwebapp.entity.Quiz;
import com.quizwebapp.mapper.CategoryRowMapper;
import com.quizwebapp.mapper.ChoiceRowMapper;
import com.quizwebapp.mapper.QuestionRowMapper;
import com.quizwebapp.mapper.QuizRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizDao {
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    CategoryRowMapper categoryRowMapper;
    QuestionRowMapper questionRowMapper;
    ChoiceRowMapper choiceRowMapper;
    QuizRowMapper quizRowMapper;
    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                   CategoryRowMapper categoryRowMapper, QuestionRowMapper questionRowMapper,
                   ChoiceRowMapper choiceRowMapper, QuizRowMapper quizRowMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
        this.questionRowMapper = questionRowMapper;
        this.choiceRowMapper = choiceRowMapper;
        this.quizRowMapper = quizRowMapper;
    }

    public List<Category> getAllCategory() {
        String query = "SELECT * FROM Category";

        return jdbcTemplate.query(query, categoryRowMapper);
    }

    public Category getCategoryById(int id) {
        String query = "SELECT * FROM Category WHERE category_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        List<Category> categoryList = namedParameterJdbcTemplate.query(query, parameterSource, categoryRowMapper);

        return categoryList.size() == 1 ? categoryList.get(0) : null;
    }

    public List<Question> getFiveRandomQuizByCategoryId(int categoryId) {
        String query = "SELECT * FROM Question WHERE category_id = :category_id ORDER BY RAND() LIMIT 5";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("category_id", categoryId);

        return namedParameterJdbcTemplate.query(query, parameterSource, questionRowMapper);
    }

    public List<Choice> getChoiceByQuestionId(int questionId) {
        String query = "SELECT * FROM Choice WHERE question_id = :questionId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("questionId", questionId);

        return namedParameterJdbcTemplate.query(query, parameterSource, choiceRowMapper);
    }

    public Quiz createQuiz(int userId, int categoryId, String quizName, Timestamp timeStart) {
        String query = "INSERT INTO Quiz (user_id, category_id, quiz_name, time_start) VALUES (:userId, :categoryId, :quizName, :timeStart)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", userId);
        parameterSource.addValue("categoryId", categoryId);
        parameterSource.addValue("quizName", quizName);
        parameterSource.addValue("timeStart", timeStart);


        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, parameterSource, keyHolder);

        // Assuming quiz_id is the auto-generated key
        Number key = keyHolder.getKey();
        if (key != null) {
            System.out.println(getQuizById(key.intValue()));
            // Retrieve and return the created Quiz object
            return getQuizById(key.intValue());
        } else {
            // Handle the case where quiz creation failed
            return null;
        }
    }

    public Quiz getQuizById(int quizId) {
        String query = "SELECT * FROM Quiz WHERE quiz_id = :quizId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("quizId", quizId);

        List<Quiz> quizList = namedParameterJdbcTemplate.query(query, parameterSource, quizRowMapper);

        return !quizList.isEmpty() ? quizList.get(0) : null;
    }

    public Choice getCorrectAnswerWithQuestionId(int questionId) {
        String sql = "SELECT * FROM Choice WHERE question_id = :questionId AND is_correct = TRUE";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("questionId", questionId);

        List<Choice> choices = namedParameterJdbcTemplate.query(sql, parameterSource, choiceRowMapper);

        return !choices.isEmpty() ? choices.get(0) : null;
    }

    public boolean addQuizQuestionWithoutUserChoice(int quizId, int questionId) {
        String query = "INSERT INTO QuizQuestion (quiz_id, question_id) VALUES (?, ?)";
        System.out.println("quizId = " + quizId);
        System.out.println("questionId = " + questionId);
        return jdbcTemplate.update(query, quizId, questionId) == 1;
    }

    public boolean updateQuizEndTimeWithQuizId(int quizId, Timestamp endTime) {
        String query = "UPDATE Quiz SET time_end = :endTime WHERE quiz_id = :quizId;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("endTime", endTime);
        parameterSource.addValue("quizId", quizId);

        return namedParameterJdbcTemplate.update(query, parameterSource) == 1;
    }

    public Quiz getProcessingQuiz(int userId) {
        String query = "SELECT * FROM Quiz WHERE user_id = :userId AND time_end IS NULL";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", userId);

        List<Quiz> processQuiz = namedParameterJdbcTemplate.query(query, parameterSource, quizRowMapper);

        return !processQuiz.isEmpty() ? processQuiz.get(0) : null;
    }

    public List<Integer> getQuestionIdListByQuizId(int quizId) {
        String query = "SELECT question_id FROM QuizQuestion WHERE quiz_id = :quizId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("quizId", quizId);

        RowMapper<Integer> rowMapper = (rs, rowNum) -> rs.getInt("question_id");
        return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
    }

    public Question getQuestionById(int questionId) {
        String query = "SELECT * FROM Question WHERE question_id = :questionId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("questionId", questionId);

        List<Question> questions = namedParameterJdbcTemplate.query(query, parameterSource, questionRowMapper);

        return !questions.isEmpty() ? questions.get(0) : null;
    }

    public boolean updateQuestionWithUserChoice(int quizId, int questionId, int userChoiceId) {
        String query = "UPDATE QuizQuestion SET user_choice_id = :userChoiceId WHERE quiz_id = :quizId AND question_id = :questionId";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userChoiceId", userChoiceId);
        parameterSource.addValue("quizId", quizId);
        parameterSource.addValue("questionId", questionId);

        int rowsAffected = namedParameterJdbcTemplate.update(query, parameterSource);
        return rowsAffected > 0;
    }

    public int getUserChoiceIdByQuizIdAndQuestionId(int quizId, int questionId) {
        String query = "SELECT * FROM QuizQuestion WHERE quiz_id = :quizId AND question_id = :questionId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("questionId", questionId);
        parameterSource.addValue("quizId", quizId);
        RowMapper<Integer> choiceRowMapper = new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("user_choice_id");
            }
        };

        List<Integer> choices = namedParameterJdbcTemplate.query(query, parameterSource, choiceRowMapper);

        return !choices.isEmpty() ? choices.get(0) : -1;
    }

    public Choice getChoiceById(int choiceId) {
        String query = "SELECT * FROM Choice WHERE choice_id = :choiceId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("choiceId", choiceId);

        List<Choice> choices = namedParameterJdbcTemplate.query(query, parameterSource, choiceRowMapper);

        return !choices.isEmpty() ? choices.get(0) : null;
    }

    public List<Quiz> getAllQuizByUserId(int userId) {
        String query = "SELECT * FROM Quiz WHERE user_id = :userId AND time_end IS NOT NULL";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", userId);

        List<Quiz> quizList = namedParameterJdbcTemplate.query(query, parameterSource, quizRowMapper);

        return !quizList.isEmpty() ? quizList : new ArrayList<>();
    }
}
