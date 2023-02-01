package com.flamexander.rabbitmq.console.consumer;

import com.flamexander.rabbitmq.console.FactoryUtil;
import com.rabbitmq.client.*;

public class RMQElementsReceiverApp {
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = FactoryUtil.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println(" [*] Waiting for messages");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume("ReadyQueue", true, deliverCallback, consumerTag -> {
        });
    }
}
