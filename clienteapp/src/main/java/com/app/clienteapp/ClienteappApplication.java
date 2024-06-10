package com.app.clienteapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.app.clienteapp.model")

public class ClienteappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteappApplication.class, args);
    }

}
