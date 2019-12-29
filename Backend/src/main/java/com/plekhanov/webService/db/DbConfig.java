package com.plekhanov.webService.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import javax.sql.DataSource;
import java.io.File;


@Configuration
public class DbConfig {

    @Value("db.url")
    private String url;
    @Value("db.username")
    private String username;
    @Value("db.password")
    private String password;


    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();

    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource getDataSource) {
        return new JdbcTemplate(getDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource getDataSource) {
        return new NamedParameterJdbcTemplate(getDataSource);
    }
}
