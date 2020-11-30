package com.example.musicstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MusicStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicStoreApplication.class, args);
    }

}
