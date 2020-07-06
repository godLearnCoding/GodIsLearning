package mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mq
 * 说明：rabbit消息中间件API
 * 日期：2020年07月03日
 * 备注：
 * </pre>
 */
public class RabbitMQTest {

  private static  ConnectionFactory connectionFactory = new ConnectionFactory();

  static {
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    connectionFactory.setVirtualHost("/");
    connectionFactory.setHost("127.0.0.1");
    connectionFactory.setPort(5672);
  }

  public static void main(String[] args) throws IOException, TimeoutException {
    Connection connection = connectionFactory.newConnection();
    Channel channel = connection.createChannel();
    String exchangeName = "exchange-test";
    String routingkey = "routingkey-test";
    channel.exchangeDeclare(exchangeName,"direct",true);
    String queueName = channel.queueDeclare("queueDemo",true,false,false,null).getQueue();
    channel.queueBind(queueName,exchangeName,routingkey);
    byte[] msg = "hello,rabbitMQ".getBytes();
    channel.basicPublish(exchangeName,routingkey, MessageProperties.PERSISTENT_TEXT_PLAIN,msg);
    connection.close();
  }

}
