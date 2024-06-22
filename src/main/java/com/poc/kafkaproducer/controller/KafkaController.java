package com.poc.kafkaproducer.controller;


import com.poc.kafkaproducer.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka topic!";
    }

    @PostMapping("/v2/send")
    public String sendMessage(@RequestParam("key") String key, @RequestParam("message") String message) {
        kafkaProducer.sendMessage(key, message);
        return "Message sent successfully to kafka topic";
    }
}

