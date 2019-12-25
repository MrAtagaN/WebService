package com.plekhanov.webService.dao.impl;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** Mapper */
    private final RowMapper<User> rowMapper = ((resultSet, i) -> {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setLastEnter(resultSet.getTimestamp("lastEnter").toLocalDateTime());
        return user;
    });

    public User findPersonById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",id);
        return namedParameterJdbcTemplate.queryForObject("select * from PERSON where PERSON.id = ?", params, rowMapper);
    }
}
