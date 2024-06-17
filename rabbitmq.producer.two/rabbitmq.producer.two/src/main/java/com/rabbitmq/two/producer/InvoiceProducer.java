package com.rabbitmq.two.producer;

import com.rabbitmq.two.entity.InvoiceCancelledMessage;
import com.rabbitmq.two.entity.InvoiceCreatedMessage;
import com.rabbitmq.two.entity.InvoicePaidMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

    private static final String EXCHANGE = "x.invoice";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendInvoiceCreated(InvoiceCreatedMessage invoiceCreatedMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", invoiceCreatedMessage);
    }

    public void sendInvoicePaid(InvoicePaidMessage invoicePaidMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", invoicePaidMessage);
    }

    public void sendInvoiceCancelled(InvoiceCancelledMessage invoiceCancelledMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", invoiceCancelledMessage);
    }
}
