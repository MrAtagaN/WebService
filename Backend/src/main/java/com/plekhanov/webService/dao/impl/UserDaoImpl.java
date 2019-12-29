package com.plekhanov.webService.dao.impl;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.dao.UserRoleDao;
import com.plekhanov.webService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Autowired
    UserRoleDao userRoleDao;


    public UserDaoImpl() {
        super((resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setAccountNonExpired(resultSet.getBoolean("account_non_expired"));
            user.setAccountNonLocked(resultSet.getBoolean("account_non_locked"));
            user.setCredentialsNonExpired(resultSet.getBoolean("credentials_is_non_expired"));
            user.setEnabled(resultSet.getBoolean("enabled"));
            user.setLastEnter(resultSet.getObject("last_enter", LocalDateTime.class));
            return user;
        });
    }


    @Override
    public User findByName(String userName) {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);

        return DataAccessUtils.singleResult(namedParameterJdbcTemplate.query(
                "select * from users where username = :userName", params, mapper));
    }

    @Override
    String getTableName() {
        return "users";
    }


    @Override
    String getUpdateQuery() {
        return "update table users set " +
                " username := username " +
                " password := password " +
                " account_non_expired := accountNonExpired " +
                " account_non_locked := accountNonLocked" +
                " credentials_is_non_expired := credentialsNonExpired" +
                " enabled := enabled" +
                " last_enter := lastEnter";
    }

    @Override
    String getInsertQuery() {
        return "insert into users (" +
                " username, " +
                " password, " +
                " account_non_expired, " +
                " account_non_locked, " +
                " credentials_is_non_expired, " +
                " enabled, " +
                " last_enter) values (" +
                " :username, " +
                " :password," +
                " :accountNonExpired," +
                " :accountNonLocked," +
                " :credentialsNonExpired," +
                " :enabled," +
                " :lastEnter)";
    }


    @Override
    Map<String, Object> getParamMap(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        params.put("accountNonExpired", user.isAccountNonExpired());
        params.put("accountNonLocked", user.isAccountNonLocked());
        params.put("credentialsNonExpired", user.isCredentialsNonExpired());
        params.put("enabled", user.isEnabled());
        params.put("lastEnter", user.getLastEnter());

        return params;
    }
}
