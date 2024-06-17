package com.rabbitmq.two.consumer;

import com.rabbitmq.two.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.concurrent.TimeUnit;

public class DummyPrefetchConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyPrefetchConsumer.class);

    @RabbitListener(queues = "q.dummy", concurrency = "2")
    public void listenDummy(DummyMessage dummyMessage) throws InterruptedException {
        LOG.info("Message is { }", dummyMessage);

        TimeUnit.SECONDS.sleep(20);
    }
}
