package com.remotelabs.hire.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    /**
     * 
     * Nothing happening here yet
     */
    @KafkaListener(topics = "messages", groupId = "groupId")
    public void sendMessage(String message) {

        log.info("Listener received data: " + message);
    }
}
