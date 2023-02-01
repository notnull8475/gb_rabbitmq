package com.flamexander.rabbitmq.console.consumer;

import com.flamexander.rabbitmq.console.FactoryUtil;
import com.rabbitmq.client.*;

public class ExchangeReceiverApp {
    private static final String EXCHANGE_NAME = "directExchanger";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = FactoryUtil.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();
        System.out.println("My queue name: " + queueName);

        channel.queueBind(queueName, EXCHANGE_NAME, "php");

        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
