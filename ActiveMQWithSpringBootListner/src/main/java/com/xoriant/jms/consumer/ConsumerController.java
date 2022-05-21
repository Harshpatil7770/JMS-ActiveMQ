package com.xoriant.jms.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {

	@JmsListener(destination = "springBootWithMQ")
	public void messageConsumer(String message) {
		System.out.println(message);
	}
}
