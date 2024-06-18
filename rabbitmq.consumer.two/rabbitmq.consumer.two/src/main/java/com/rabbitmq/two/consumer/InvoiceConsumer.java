package com.rabbitmq.two.consumer;

import com.rabbitmq.two.entity.InvoiceCancelledMessage;
import com.rabbitmq.two.entity.InvoiceCreatedMessage;
import com.rabbitmq.two.entity.InvoicePaidMessage;
import com.rabbitmq.two.entity.PaymentCancelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

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

    @RabbitHandler
    @SendTo("x.invoice.cancel/")
    public PaymentCancelStatus handleInvoiceCancelled(InvoiceCancelledMessage invoiceCancelledMessage) {
        var randomStatus = ThreadLocalRandom.current().nextBoolean();

        return new PaymentCancelStatus(randomStatus, LocalDate.now(), invoiceCancelledMessage.getInvoiceNumber());
    }

}
