package com.quizwebapp.mapper;

import com.quizwebapp.entity.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("contact_id"));
        contact.setSubject(rs.getString("contact_subject"));
        contact.setMessage(rs.getString("message"));
        contact.setEmail(rs.getString("email"));
        contact.setContactTime(rs.getTimestamp("contact_time").toLocalDateTime());

        return contact;
    }
}
