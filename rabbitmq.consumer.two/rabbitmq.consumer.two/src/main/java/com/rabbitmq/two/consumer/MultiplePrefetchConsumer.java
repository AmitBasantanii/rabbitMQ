package com.rabbitmq.two.consumer;

import com.rabbitmq.two.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class MultiplePrefetchConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MultiplePrefetchConsumer.class);

    @RabbitListener(queues = "q.transaction", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
    // method to listen
    public void listenTransaction(DummyMessage dummyMessage) throws InterruptedException {
        LOG.info("Consuming transaction : {}", dummyMessage.getContent());

        TimeUnit.SECONDS.sleep(1);
    }

    @RabbitListener(queues = "q.scheduler", concurrency = "2")
    public void listenScheduler(DummyMessage dummyMessage) throws InterruptedException {
        LOG.info("Consuming scheduler : {}", dummyMessage.getContent());

        TimeUnit.SECONDS.sleep(60);
    }
}

// RabbitMQ container factory for the q.scheduler listener