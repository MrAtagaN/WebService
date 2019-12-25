package com.plekhanov.webService.web;

import com.plekhanov.webService.dao.PersonDAO;
import com.plekhanov.webService.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    PersonDAO personDAO;

    @GetMapping("/getUser")
    public List<Person> getPerson() {
        List<Person> persons = new ArrayList<>();
        persons.add(personDAO.findPersonById(1));
        return persons;
    }
}
