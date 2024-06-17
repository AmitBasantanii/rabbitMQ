package com.rabbitmq.two.consumer;

import com.rabbitmq.two.entity.InvoiceCreatedMessage;
import com.rabbitmq.two.entity.InvoicePaidMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceConsumer.class);

    @RabbitHandler
    public void handleInvoiceCreated(InvoiceCreatedMessage invoiceCreatedMessage) {
        LOG.info("Invoice Created : {}", invoiceCreatedMessage);
    }

    @RabbitHandler
    public void handleInvoicePaid(InvoicePaidMessage invoicePaidMessage) {
        LOG.info("Invoice Paid : {}", invoicePaidMessage);
    }

    @RabbitHandler(isDefault = true)
    public void handleDefault(Object message) {
        LOG.info("Default Handler : {}", message);
    }

}
