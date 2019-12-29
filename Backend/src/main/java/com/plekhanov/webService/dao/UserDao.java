package com.plekhanov.webService.dao;

import com.plekhanov.webService.entities.User;


public interface UserDao extends BaseDao<User, Long>{

    User findByName(String userName);
}
