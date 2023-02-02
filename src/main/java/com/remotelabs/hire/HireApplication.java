package com.remotelabs.hire;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class HireApplication {

    public static void main(String[] args) {
        SpringApplication.run(HireApplication.class, args);
    }
}
