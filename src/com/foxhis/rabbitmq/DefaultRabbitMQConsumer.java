package com.foxhis.rabbitmq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class DefaultRabbitMQConsumer extends DefaultConsumer {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public DefaultRabbitMQConsumer(Channel channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handleDelivery(String consumerTag,
			Envelope envelope,
			AMQP.BasicProperties properties,
			byte[] body)
					throws IOException
	{
		String message = new String(body, "UTF-8");
        System.out.println("Customer Received [" + message + "]");
        logger.info("Customer Received [\" + message + \"]");
	}

}
