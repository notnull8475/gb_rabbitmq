package ru.gb.rabbitmq.console.homework.blog;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.gb.rabbitmq.console.homework.ConnFactUtil;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ArticleSendApp {


    public static void main(String[] args) {
        System.out.println("publisher.Publisher");

        ConnectionFactory factory = ConnFactUtil.getFactory();

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(ConnFactUtil.EXCHANGER_NAME, BuiltinExchangeType.DIRECT);

            while (true) {

                Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();
                String lang = message.substring(0,message.indexOf(' '));
                String content = message.substring(message.indexOf(' '));


                if (!lang.trim().isEmpty()) {
                    channel.basicPublish(ConnFactUtil.EXCHANGER_NAME, lang, null, content.getBytes());
                    System.out.println("Статья опубликована");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
