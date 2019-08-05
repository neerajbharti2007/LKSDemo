/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Neeraj
 */
@Configuration
@EnableAutoConfiguration(exclude = { //
    DataSourceAutoConfiguration.class, //
    DataSourceTransactionManagerAutoConfiguration.class})
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.lks.*"})
public class AppConfig {
    
    @Autowired
    private Environment env;
    
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("mysql.datasource.url"));
        dataSourceBuilder.username(env.getProperty("mysql.datasource.username"));
        dataSourceBuilder.password(env.getProperty("mysql.datasource.password"));
        dataSourceBuilder.driverClassName(env.getProperty("mysql.datasource.driver-class-name"));
        return dataSourceBuilder.build();

    }

}
