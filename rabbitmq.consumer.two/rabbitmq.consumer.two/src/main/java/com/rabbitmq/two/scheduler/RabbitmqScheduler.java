package com.rabbitmq.two.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class RabbitmqScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitmqScheduler.class);

    @Autowired
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    @Scheduled(cron = "0 15 6 * * *")
    // 11 PM as cron = "0 0 23 * * *"
    public void stopAll() {
        rabbitListenerEndpointRegistry.getListenerContainers().forEach(c -> {
            LOG.info("Stopping Container {}", c);
            c.stop();
        });
    }

    @Scheduled(cron = "1 17 6 * * *")
    // starts 1 second after mid night as cron = "1 0 0 * * *"
    public void startAll() {
        rabbitListenerEndpointRegistry.getListenerContainers().forEach(c -> {
            LOG.info("Starting container {}", c);
            c.stop();
        });
    }
}
