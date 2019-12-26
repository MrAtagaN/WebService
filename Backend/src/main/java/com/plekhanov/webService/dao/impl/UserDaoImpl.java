package com.plekhanov.webService.dao.impl;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.entities.User;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

    @Override
    String getTableName() {
        return "USERS";
    }

    @Override
    String getUpdateQuery() {
        return "update USERS set id=:id, name=:name, last_enter=:lastEnter";
    }

    @Override
    String getInsertQuery() {
        return "insert into USERS (id, name, last_enter) values (:id, :name, :lastEnter)";
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
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            //user.setLastEnter(resultSet.getObject("last_enter", LocalDateTime.class));  //SqLite не поддерживает
            return user;
        });
    }

}
