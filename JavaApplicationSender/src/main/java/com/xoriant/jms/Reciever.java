package com.xoriant.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Reciever {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("Info1");
			MessageConsumer messageConsumer = session.createConsumer(destination);
			messageConsumer.setMessageListener(new MessageListener() {

				public void onMessage(Message message) {
					TextMessage textMessage = (TextMessage) message;
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
