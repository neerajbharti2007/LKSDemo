/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Neeraj
 */
@SpringBootApplication
@EnableSwagger2
public class SpringBoot2RestServiceApplication {
    
    public static void main(String[] args) {
		SpringApplication.run(SpringBoot2RestServiceApplication.class, args);
	}
    
}
