package ru.gb.rabbitmq.console.homework.subscriber;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import ru.gb.rabbitmq.console.homework.ConnFactUtil;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ReceiverApp {

    public static void main(String[] args) throws IOException, TimeoutException {

        boolean flag = true;
        System.out.println("Выберите тему введя <<set_topic [название языка]>>");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String command = input.substring(0, input.indexOf(' '));
        String theme = input.substring(input.indexOf(' ')).trim();
        if (command.equals("set_topic")) {
            System.out.println("выбрана тема [" + theme+"]");
            Connection connection = ConnFactUtil.getFactory().newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(ConnFactUtil.EXCHANGER_NAME, BuiltinExchangeType.DIRECT);
            String queueName = channel.queueDeclare().getQueue();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("Содержание статьи: " + message);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
            channel.queueBind(queueName, ConnFactUtil.EXCHANGER_NAME, theme);
        }

    }
}
