package com.xoriant.delivery.queue;

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

public class Consumer {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			// If you have business logic and after exceuting all succesfully steps if you
			// want
			// to display the message then we will go for CLIENT_ACKNOWLEGEMNT
			Destination destination = session.createQueue("greet");
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				// (new MessageListner()) is an interface
				public void onMessage(Message message) {
					TextMessage textMessage = (TextMessage) message;
					try {
						textMessage.acknowledge();
						System.out.println(textMessage.getText());

					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
