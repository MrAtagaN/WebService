package com.plekhanov.webService.web;

import com.plekhanov.webService.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;


    @GetMapping
    public String getLogin(HttpServletRequest httpServletRequest) {
        CsrfToken csrf = (CsrfToken)httpServletRequest.getAttribute("_csrf");
        System.out.println(csrf.getToken());
        return "Secure";
    }


}
