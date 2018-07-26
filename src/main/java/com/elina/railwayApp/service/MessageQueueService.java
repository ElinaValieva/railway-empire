package com.elina.railwayApp.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Log4j
public class MessageQueueService {

    private static final String EXCHANGE_NAME = "messages";

    public void produceMsg(String msg) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
        channel.basicPublish("", EXCHANGE_NAME, null, msg.getBytes("UTF-8"));
        log.info(" [x] Sent '" + msg + "'");

        channel.close();
        connection.close();
    }
}
