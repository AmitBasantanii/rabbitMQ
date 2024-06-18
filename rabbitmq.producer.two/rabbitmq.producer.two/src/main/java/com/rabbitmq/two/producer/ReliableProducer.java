package com.rabbitmq.two.producer;

import com.rabbitmq.two.entity.DummyMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Service
public class ReliableProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReliableProducer.class);
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @PostConstruct
    private void registerCallback() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, reason) -> {
            if(correlationData == null) {
                return;
            }
            
            if(ack) {
                LOG.info("Message with correlation {} is published", correlationData.getId());
            }
            else {
                LOG.warn("Invalid Exchange. Message with correlation {} is NOT published", correlationData.getId());
            }
        });
        
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            LOG.info("Return callback");
            
            if(returnedMessage.getReplyText() != null && returnedMessage.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
                var id = returnedMessage.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation").toString();

                LOG.warn("Invalid routing key for id : {}", id);
            }
        });
    }

    public void sendDummyWithInvalidRoutingKey(DummyMessage dummyMessage) {
        var correlationData = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend("x.dummy", "invalidRoutingKey", dummyMessage, correlationData);

    }

    public void sendDummyToInvalidExchange(DummyMessage dummyMessage) {
        var correlationData = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend("invalidExchange", "", dummyMessage, correlationData);

    }
}
