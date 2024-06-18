package com.rabbitmq.two;

import com.rabbitmq.two.entity.*;
import com.rabbitmq.two.producer.DummyProducer;
import com.rabbitmq.two.producer.InvoiceProducer;
import com.rabbitmq.two.producer.MultiplePrefetchProducer;
import com.rabbitmq.two.producer.ReliableProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Autowired
//	private DummyProducer dummyProducer;

//	@Autowired
//	private MultiplePrefetchProducer multiplePrefetchProducer;

//	@Autowired
//	private InvoiceProducer invoiceProducer;

//	@Autowired
//	private SingleActiveProducer singleActiveProducer;

//	@Autowired
//	private ReliableProducer reliableProducer;

	@Autowired
	private InvoiceProducer invoiceProducer;

	@Override
	public void run(String... args) throws Exception {
//		var dummyMessage = new DummyMessage("Content", 1);

//		dummyProducer.sendDummy(dummyMessage);

//		for (int i =0; i<500 ; i++) {
//			var dummyMessage = new DummyMessage("Content " + i, 1);
//
//			dummyProducer.sendDummy(dummyMessage);
////			TimeUnit.SECONDS.sleep(1);
//		}

//		multiplePrefetchProducer.simulateTransaction();
//		multiplePrefetchProducer.simulateScheduler();
//		System.out.println("All data sent");

//		// another task - one queue + multiple message types
//		var randomInvoiceNumber = "INV- " + ThreadLocalRandom.current().nextInt(100, 200);
//		var invoiceCreatedMessage = new InvoiceCreatedMessage(155.75, LocalDate.now(), "USD", randomInvoiceNumber);
//		invoiceProducer.sendInvoiceCreated(invoiceCreatedMessage);
//
//		// to refresh invoice number
//		randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
//		// generate random payment number
//		var  randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(1000,2000);
//
//		// generate message method
//		var invoicePaidMessage = new InvoicePaidMessage(randomInvoiceNumber, LocalDate.now(), randomPaymentNumber);
//
//		// sending the message
//		invoiceProducer.sendInvoicePaid(invoicePaidMessage);
//
//		// sending the dummy data from the cancelled message
//		randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(300,400);
//
//		var invoiceCancelledMessage = new InvoiceCancelledMessage(LocalDate.now(), randomInvoiceNumber, "Invoice Canceled");
//		invoiceProducer.sendInvoiceCancelled(invoiceCancelledMessage);

		// another project
//		singleActiveProducer.sendDummy();

//		// another project
//		var dummyMessage = new DummyMessage("Dummy content", 1);
//
//		System.out.println("--------------------------------------------");
//		System.out.println("Calling sendDummyToInvalidExchange()");
//		reliableProducer.sendDummyToInvalidExchange(dummyMessage);
//
//		System.out.println("-----------------------------------------------");
//		System.out.println("Calling sendDummyWithInvalidRoutingKey()");
//		reliableProducer.sendDummyWithInvalidRoutingKey(dummyMessage);

		// another one - Request / Reply - Payment cancellation
		for(int i=0; i<10; i++) {
			var invoiceNumber = "INV-" + i;
			var invoiceCancelledMessage = new InvoiceCancelledMessage(LocalDate.now(), invoiceNumber,
					"Invoice cancelled " + i);

			invoiceProducer.sendInvoiceCancelled(invoiceCancelledMessage);
		}
	}
}
