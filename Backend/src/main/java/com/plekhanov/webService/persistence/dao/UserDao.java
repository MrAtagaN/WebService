package com.plekhanov.webService.persistence.dao;

import com.plekhanov.webService.persistence.entities.User;

import javax.validation.constraints.NotNull;


public interface UserDao extends BaseDao<User, Long>{

    User findByName(@NotNull String userName);
}
