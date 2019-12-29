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
            return Role.valueOf(resultSet.getString("name"));
        };
    }


    @Override
    public Set<Role> findRolesByUserId(long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return new HashSet<>(namedParameterJdbcTemplate.query("select user_id, name from role WHERE user_id = :userId", params, mapper));
    }


    @Override
    public void addRoleToUser(Role role, Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", role.name());
        params.put("userId", userId);
        namedParameterJdbcTemplate.query("insert into role ( user_id, name ) values (:userId, :roleName)", params, mapper);
    }


    @Override
    public void deleteRoleOfUser(Role role, Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", role.name());
        params.put("userId", userId);
        namedParameterJdbcTemplate.query("delete from role WHERE user_id = :userId and name = :roleName", params, mapper);
    }
}
