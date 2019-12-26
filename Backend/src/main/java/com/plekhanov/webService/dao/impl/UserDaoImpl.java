package com.plekhanov.webService.dao.impl;


import com.plekhanov.webService.entities.User;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> {

    @Override
    String getTableName() {
        return "USERS";
    }

    @Override
    String getUpdateQuery() {
        return "update USERS set id=:id, name=:name, lastEnter=:lastEnter";
    }

    @Override
    String getInsertQuery() {
        return "insert into USERS (id, name, lastEnter) values (:id, :name, :lastEnter)";
    }

    @Override
    Map<String, Object> getParamMap(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId);
        params.put("name", user.getName());
        params.put("lastEnter", user.getLastEnter());
        return params;
    }

    public UserDaoImpl() {
        super((resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastEnter(resultSet.getObject("lastEnter", LocalDateTime.class));
            return user;
        });
    }

}
