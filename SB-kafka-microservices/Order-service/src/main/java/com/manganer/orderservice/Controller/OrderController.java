package com.manganer.orderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final String TOPIC = "order-topic";

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        kafkaTemplate.send(TOPIC, order);
        return ResponseEntity.ok("Order sent to Kafka: " + order.toString());
    }
}
