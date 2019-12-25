package com.plekhanov.webService.web;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("info/{id}")
    public User getInfo(@PathVariable Integer id) {
        return userDao.findPersonById(id);
    }

}
