package com.uni.project.library.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    /*
    * Използваме го за да настроим flyway така че първо hibernate да създава таблиците,
    * а след това flyway да пусне скриптовете, които добавят данни в таблиците.
    * */
    @Autowired
    public FlywayConfiguration(DataSource dataSource) {
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }
}