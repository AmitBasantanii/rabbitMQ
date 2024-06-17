package com.rabbitmq.two.consumer;

import com.rabbitmq.two.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SingleActiveConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SingleActiveConsumer.class);

    @RabbitListener(queues = "q.single", concurrency = "5")
    public void listenDummy(DummyMessage dummyMessage) throws InterruptedException {
        LOG.info("{}", dummyMessage);
        TimeUnit.SECONDS.sleep(1);
    }
}
