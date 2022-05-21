package com.xoriant.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic("TopicQ");
			MessageProducer messageProducer = session.createProducer(destination);
			TextMessage message = session.createTextMessage("Message For Topic");

			messageProducer.send(message);
			System.out.println(message.getText());
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
