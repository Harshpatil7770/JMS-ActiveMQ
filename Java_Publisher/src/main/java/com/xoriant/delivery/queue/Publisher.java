package com.xoriant.delivery.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("greet");
			String messages[] = { "First Message", "Second Message", "Third Message", "Fourth Message" };
			MessageProducer messageProducer = session.createProducer(destination);
			for (String eachMsg : messages) {
				TextMessage textMessage = session.createTextMessage(eachMsg);
				messageProducer.send(textMessage);
			}
			// TextMessage message = session.createTextMessage("Hello from JMS");
			System.out.println("Message Published");
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
