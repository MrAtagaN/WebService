package com.plekhanov.webService.dao.impl;

import com.plekhanov.webService.dao.UserRoleDao;
import com.plekhanov.webService.web.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RowMapper<Role> mapper;


    public UserRoleDaoImpl() {
        this.mapper = (resultSet, i) -> {
            return Role.valueOf(resultSet.getString("role"));
        };
    }


    @Override
    public Set<Role> findRolesByUserId(long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return new HashSet<>(namedParameterJdbcTemplate.query("select user_id, role from user_role WHERE user_id = :userId", params, mapper));
    }


    @Override
    public void addRoleToUser(long userId, Role role) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", role.name());
        params.put("userId", userId);
        namedParameterJdbcTemplate.update("insert into user_role ( user_id, role ) values (:userId, :roleName)", params);
    }


    @Override
    public void deleteRoleOfUser(long userId, Role role) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", role.name());
        params.put("userId", userId);
        namedParameterJdbcTemplate.update("delete from user_role WHERE user_id = :userId and role = :roleName", params);
    }
}
