package com.plekhanov.webService.dao;

import com.plekhanov.webService.entities.User;

import javax.validation.constraints.NotNull;


public interface UserDao extends BaseDao<User, Long>{

    User findByName(@NotNull String userName);
}
