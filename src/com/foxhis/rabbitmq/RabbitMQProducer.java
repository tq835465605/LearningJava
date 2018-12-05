package com.foxhis.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消费生产者
 * @author Administrator
 *
 */
public class RabbitMQProducer {

	public final static String QUEUE_NAME="rabbitMQ.test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		//申明一个连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		//factory.setUsername("lp");
		//factory.setPassword("");
		//factory.setPort(2088);
		//创建一个连接
		Connection connection =factory.newConnection();
		//创建一个通道
		Channel channel =connection.createChannel();
		//声明一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "hello rabbitmq";
		//发送消息到队列中
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

		System.out.println("Producer publish message "+message);
		//关闭通道
		channel.close();
		connection.close();
	}

}
