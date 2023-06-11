package com.tfg.proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TfgApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TfgApplication.class, args);
    }
}
