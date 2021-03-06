package com.xoriant.sender.JSONPublisher;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

public class Sender {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("jsonQ");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("first_name", "Harshwardhan");
			jsonObject.put("last_name", "patil");
			jsonObject.put("email_address", "harshawardhanapatil777@gmail.com");
			jsonObject.put("mobile_number", "88303482589");

			TextMessage textMessage = session.createTextMessage(jsonObject.toString());
			MessageProducer messageProducer = session.createProducer(destination);
			messageProducer.send(textMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
