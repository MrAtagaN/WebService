package com.plekhanov.webService.dao.impl;

import com.plekhanov.webService.dao.UserAuthInfoDao;
import com.plekhanov.webService.entities.UserAuthInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserAuthInfoDaoImpl extends BaseDaoImpl<UserAuthInfo, Long> implements UserAuthInfoDao {

    public UserAuthInfoDaoImpl() {
        super((resultSet, i) -> {
            UserAuthInfo userAuthInfo = new UserAuthInfo();
            userAuthInfo.setId(resultSet.getLong("id"));
            userAuthInfo.setPassword(resultSet.getString("password"));
            userAuthInfo.setUsername(resultSet.getString("username"));

            return userAuthInfo;
        });
    }

    @Override
    public UserAuthInfo findByName(String userName) {
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
    Map<String, Object> getParamMap(UserAuthInfo userAuthInfo) {
        return null;
    }
}
