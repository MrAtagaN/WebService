package com.plekhanov.webService.entities;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class User extends BaseEntity<Integer> {

    private Integer id; //огрничение SQLite
    private String name;
    private LocalDateTime lastEnter;

}
