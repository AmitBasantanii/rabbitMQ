package com.learning.rabbitmq.producer;

import com.learning.rabbitmq.producer.entity.Employee;
import com.learning.rabbitmq.producer.entity.Furniture;
import com.learning.rabbitmq.producer.entity.Picture;
import com.learning.rabbitmq.producer.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//@EnableScheduling
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Autowired
//	private HelloRabbitProducer helloRabbitProducer;

//	@Autowired
//	private EmployeeJsonProducer producer;

//	@Autowired
//	private HumanResourceProducer producer;

//	@Autowired
//	private PictureProducer pictureProducer;

//	@Autowired
//	private PictureProducerTwo pictureProducerTwo;

//	@Autowired
//	private MyPictureProducer myPictureProducer;

	// for the furniture things on header on rabbitmq
	@Autowired
	private FurnitureProducer furnitureProducer;

	// valid sources
	private final List<String> SOURCES = List.of("mobile", "web");

	// valid types
	private final List<String> TYPES = List.of("jpg", "png", "svg");

	// for the furniture things class
	private final List<String> COLORS = List.of("white", "red", "green");

	private final List<String> MATERIALS = List.of("wood", "plastic", "steel");

	@Override
	public void run(String... args) throws Exception {
//		helloRabbitProducer.sendHello(" MyName " + ThreadLocalRandom.current().nextInt());

		// the below loop is used for the emp
//		for (int i=0; i<5; i++) {
//			var emp = new Employee("emp-" + i, "Employee" + i, Date.from(Instant.now()));
//			producer.sendMessage(emp);
//		}

		// PICTURE PRODUCER TWO CODE
//		for (int i=0; i<10; i++) {
//			var picture = new Picture();
//
//			picture.setName("Picture" + i);
//
//			// random size
//			picture.setSize(ThreadLocalRandom.current().nextLong(1,10000));
//
//			// source and type from list
//			picture.setSource(SOURCES.get( i % SOURCES.size()));
//			picture.setType(TYPES.get(i % TYPES.size()));
//
////			pictureProducer.sendMessage(picture);
//			pictureProducerTwo.sendMessage(picture);
//		}

		// MY PICTURE PRODUCER
//		for (int i=0; i<10; i++) {
//			var picture = new Picture();
//
//			picture.setName("Picture" + i);
//
//			// random size
//			picture.setSize(ThreadLocalRandom.current().nextLong(9001,10000));
//
//			// source and type from list
//			picture.setSource(SOURCES.get( i % SOURCES.size()));
//			picture.setType(TYPES.get(i % TYPES.size()));
//
////			pictureProducer.sendMessage(picture);
//			myPictureProducer.sendMessage(picture);
//		}

		for(int i=0; i<10; i++) {
			var furniture = new Furniture();

			furniture.setName("Furniture" + i);
			furniture.setColor(COLORS.get(i % COLORS.size()));
			furniture.setMaterial(MATERIALS.get(i % MATERIALS.size()));
			furniture.setPrice(i);

			furnitureProducer.sendMessage(furniture);
		}
	}
}
