package com.plekhanov.webService.dao.impl;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDaoImpl<Long, User>  {

    @Override
    String getTableName() {
        return "USERS";
    }

    @Override
    String getUpdateQuery() {
        return "";
    }

    @Override
    String getInsertQuery() {
        return null;
    }

    @Override
    Map<String, Object> getParamMap(Long aLong) {
        return null;
    }

    public UserDaoImpl() {
        super((resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastEnter(resultSet.getTimestamp("lastEnter").toLocalDateTime());
            return user;
        });
    }

}
