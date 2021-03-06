package com.xoriant.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class App {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("newQ");
			String messages[] = { "First Messages", "Second Messages", "Third Messages", "Fourth Messages" };
			MessageProducer messageProducer = session.createProducer(destination);
			for (String eachMessage : messages) {

				TextMessage textMessage = session.createTextMessage(eachMessage);
				messageProducer.send(textMessage);
			}
			System.out.println("Message Published");
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
