package com.learning.rabbitmq.consumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rabbitmq.consumer.entity.Picture;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class MyPictureImageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MyPictureImageConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag ) throws IOException {
        var picture = objectMapper.readValue(message, Picture.class);

        if (picture.getSize() > 9000) {
//            throw new AmqpRejectAndDontRequeueException("Picture size too large : " + picture);

            // the below is the line to throw the manual exception
            channel.basicReject(tag, false);
        }

        LOG.info("Processing image : {}", picture);

        channel.basicAck(tag, false);
    }
}
