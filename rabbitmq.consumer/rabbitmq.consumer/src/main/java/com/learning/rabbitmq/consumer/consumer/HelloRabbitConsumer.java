package com.learning.rabbitmq.consumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class HelloRabbitConsumer {

    @RabbitListener(queues = "learning.hello")
    public void listen(String message) {
        System.out.println("Consuming : " + message);
    }
}
