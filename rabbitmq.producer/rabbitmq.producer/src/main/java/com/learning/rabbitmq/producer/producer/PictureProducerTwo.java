package com.learning.rabbitmq.producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rabbitmq.producer.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducerTwo {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    used for json processing
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Picture picture) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(picture);
        var sb = new StringBuilder();

        // 1st word is "mobile" or "web" ( picture source )
        sb.append(picture.getSource());
        sb.append(".");

        // 2nd word is "large" or "small" based on picture size
        if (picture.getSize() > 4000) {
            sb.append("large");
        } else {
            sb.append("small");
        }
        sb.append(".");

        // 3rd word is picture type
        sb.append(picture.getType());

        var routingKey = sb.toString();
        rabbitTemplate.convertAndSend("x.picture2", routingKey, json);
    }
}
