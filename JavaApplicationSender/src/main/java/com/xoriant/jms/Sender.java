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
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("Info1");
			TextMessage message = session.createTextMessage("Hello From JMS");
			MessageProducer messageProducer = session.createProducer(destination);
			messageProducer.send(message);
			System.out.println(message);
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
