package org.bank.bankingsystem.messaging.sender;

import org.bank.bankingsystem.messaging.config.JmsConfig;
import org.bank.bankingsystem.messaging.model.MessageObject;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Sender {

    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String username) {

        System.out.println("Sending message...");

        MessageObject messageObject =
                new MessageObject(username, LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.LOGIN_QUEUE, messageObject);

        System.out.println("Message sent!");

    }
}
