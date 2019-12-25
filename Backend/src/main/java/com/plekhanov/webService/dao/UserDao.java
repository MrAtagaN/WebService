package com.plekhanov.webService.dao;

import com.plekhanov.webService.entities.User;

public interface UserDao {

    User findPersonById(int id);
}
