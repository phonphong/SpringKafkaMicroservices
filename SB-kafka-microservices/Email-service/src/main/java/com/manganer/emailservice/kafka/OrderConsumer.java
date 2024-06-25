package com.manganer.emailservice.kafka;

import com.manganer.basedomainsapplication.DTO.OrderEvent;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event) {
        LOGGER.info(String.format("Order event received in email service => %s", event.toString()));
        sendOrderConfirmationEmail(event);
    }

    private void sendOrderConfirmationEmail(OrderEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getOrder().getCustomerEmail());
        message.setSubject("Order Confirmation");
        message.setText("Dear " + event.getOrder().getCustomerName() + ",\n\nYour order with ID " + event.getOrder().getOrderId() + " has been received.\n\nThank you for your purchase!");
        mailSender.send(message);
    }
}
