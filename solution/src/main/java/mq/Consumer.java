package mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <pre>
 * 作者：shenliang
 * 项目：mq
 * 说明：消费者
 * 日期：2020年07月03日
 * 备注：
 * </pre>
 */
public class Consumer {

  private static ConnectionFactory connectionFactory = new ConnectionFactory();

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
    new Thread(()->{
      try {
        //String exchangeName = "exchage-test";
        //String routingkey = "routingkey-test";
        //channel.exchangeDeclare(exchangeName,"direct",true);
        String queueName = "queueDemo";
        //channel.queueBind(queueName,exchangeName,routingkey);
        channel.basicConsume(queueName,false,"myconsumertag",new DefaultConsumer(channel){
          @Override
          public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            String routingKey = envelope.getRoutingKey();
            String exchange = envelope.getExchange();
            long tag = envelope.getDeliveryTag();
            System.out.println(new String(body));
            channel.basicAck(tag,false);
          }
        });
      }catch (Exception e){
        e.printStackTrace();
      }


    }).start();



  }

}
