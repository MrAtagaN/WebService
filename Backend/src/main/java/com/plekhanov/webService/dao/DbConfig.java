package com.plekhanov.webService.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.File;

@Configuration
public class DbConfig {

    private static final String FS = File.separator;
    //TODO поменять URL
    private static final String URL = "jdbc:sqlite:Backend" + FS + "src" + FS + "main" + FS + "resources" + FS + "webService.db";

    @Bean("SQLite")
    public DataSource getDataSourceSqLite() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl(URL);
        dataSource.setUsername("");
        dataSource.setPassword("");
        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(20);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(@Qualifier("SQLite")DataSource getDataSource) {
        return new JdbcTemplate(getDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(@Qualifier("SQLite")DataSource getDataSource) {
        return new NamedParameterJdbcTemplate(getDataSource);
    }
}
