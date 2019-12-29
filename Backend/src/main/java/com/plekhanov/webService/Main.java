package com.plekhanov.webService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:80/
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    //TODO:
    // Тест базы
    // Тесты controllers
    // FlyWay
    // Ошибки записывать в отдельный файл
}
