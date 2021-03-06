package com.xoriant.jms.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sender")
@RestController
public class SenderController {

	@Autowired
	JmsTemplate jmsTemplte;

	@GetMapping("/send/{message}")
	public String sendMessage(@PathVariable String message) {
		jmsTemplte.send("springBootWithMQ", new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(message);
				return objectMessage;
			}
		});
		return message;
	}
}
