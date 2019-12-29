package com.plekhanov.webService.dao.impl;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super((resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setPassword(resultSet.getString("password"));
            user.setUsername(resultSet.getString("username"));
            //TODO
            return user;
        });
    }

    @Override
    public User findByName(String userName) {
        return null;
    }

    @Override
    String getTableName() {
        return null;
    }

    @Override
    String getUpdateQuery() {
        return null;
    }

    @Override
    String getInsertQuery() {
        return null;
    }

    @Override
    Map<String, Object> getParamMap(User user) {
        return null;
    }
}
