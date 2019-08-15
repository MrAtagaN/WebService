package com.plekhanov.webService.dao;

import com.plekhanov.webService.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Person findPersonById(int id) {
        return (Person) jdbcTemplate.queryForObject("select * from PERSON where PERSON.id = ?", rowMapper, id);
    }

    private final RowMapper<Person> rowMapper = (resultSet, i) -> {
        Person person = new Person();

        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));

        return person;
    };
}
