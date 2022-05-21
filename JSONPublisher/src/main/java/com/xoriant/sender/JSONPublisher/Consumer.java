package com.xoriant.sender.JSONPublisher;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

public class Consumer {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		try {
			Connection connection=connectionFactory.createConnection();
			connection.start();
			Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Destination destination=session.createQueue("jsonQ");
			JSONObject jsonObject=new JSONObject("{\"first_name\":\"Harshawardhan\",\"last_name\":\"patil\","
					+ "\"email_address\":\"harshawardhnapatil7770@gmail.com\","
					+ "\"mobile_number\":\"8830482589\",}");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
