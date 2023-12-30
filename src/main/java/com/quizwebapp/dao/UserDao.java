package com.quizwebapp.dao;

import com.quizwebapp.entity.User;
import com.quizwebapp.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    JdbcTemplate jdbcTemplate;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    UserRowMapper userRowMapper;
    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public boolean createUser(User user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("firstName", user.getFirstName());
        parameterSource.addValue("lastName", user.getLastName());
        parameterSource.addValue("password", user.getPassword());
        parameterSource.addValue("isActive", user.isActive());
        parameterSource.addValue("isAdmin", user.isAdmin());
        parameterSource.addValue("email", user.getEmail());

        String query = "INSERT INTO User (email, user_password, firstname, lastname, is_active, is_admin)"
                        + "VALUES (:email, :password, :firstName, :lastName, :isActive, :isAdmin)";

        int res = namedParameterJdbcTemplate.update(query, parameterSource);
        System.out.println("res = " + res);
        return res == 1;
    }

    public User getUserByEmail(String email) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("email", email);
        String query = "SELECT * FROM User WHERE email = :email";

        List<User> users = namedParameterJdbcTemplate.query(query, parameterSource, userRowMapper);

        return users.size() == 1 ? users.get(0) : null;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM User WHERE user_id = :userId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", userId);

        List<User> users = namedParameterJdbcTemplate.query(sql, parameterSource, userRowMapper);

        return !users.isEmpty() ? users.get(0) : null;
    }
}
