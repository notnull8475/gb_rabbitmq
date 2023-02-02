package ru.gb.rabbitmq.console.homework;

import com.rabbitmq.client.ConnectionFactory;

public class ConnFactUtil {
    public final static String EXCHANGER_NAME = "blog_exchanger";
    public static ConnectionFactory getFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");
        factory.setVirtualHost("rabbitmq");
        return factory;
    }

}
