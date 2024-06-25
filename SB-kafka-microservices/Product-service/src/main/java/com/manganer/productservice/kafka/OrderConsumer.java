package com.manganer.productservice.kafka;

import com.manganer.basedomainsapplication.DTO.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event) {
        LOGGER.info(String.format("Order event received in product service => %s", event.toString()));
        updateProductStock(event);
    }

    private void updateProductStock(OrderEvent event) {

        LOGGER.info(String.format("Updating product stock for order: %s", event.getOrder().getOrderId()));

    }
}
