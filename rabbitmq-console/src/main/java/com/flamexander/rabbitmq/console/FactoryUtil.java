package com.flamexander.rabbitmq.console;

import com.rabbitmq.client.ConnectionFactory;

public class FactoryUtil {
    public static ConnectionFactory getFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");
        factory.setVirtualHost("rabbitmq");
        return factory;
    }
}
