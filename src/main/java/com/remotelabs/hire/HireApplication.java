package com.remotelabs.hire;

import com.remotelabs.hire.services.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class HireApplication {

    private final KafkaProducerService kafkaProducerService;

    public static void main(String[] args) {
        SpringApplication.run(HireApplication.class, args);
    }
}
