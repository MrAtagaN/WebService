package com.plekhanov.webService.web;

import com.plekhanov.webService.dao.UserDao;
import com.plekhanov.webService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;


    @GetMapping("info/{id}")
    public User getInfo(@PathVariable Integer id) {
        return userDao.findById(id);
    }


    @GetMapping("create")
    public Integer createUser() {
        User user = new User();

        user.setId(100);
        user.setName("atagan_" + new Random().nextInt(100)); //
        user.setLastEnter(LocalDateTime.now());

        return userDao.saveOrUpdate(user);
    }



}
