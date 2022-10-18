package rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static Runnable runnable = new Runnable() {
        public void run() {
            // 所有中间件都是基于tcp/ip协议基础之上构建新的协议规范,只不过rabbitmq遵循的是amqp
            // ip 端口

            // 1 创建链接工程
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("119.29.104.21");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("yukicris");
            connectionFactory.setPassword("flzx3000c");
            connectionFactory.setVirtualHost("/");

            final String queneName = Thread.currentThread().getName();
            Connection connection = null;
            Channel channel = null;
            try {
                // 2 创建链接Connection
                connection = connectionFactory.newConnection("生产者");
                // 3 通过链接获取通道Channel
                channel= connection.createChannel();
                // 4 通过通道创建交换机,队列,绑定关系,路由key,发送消息,接受消息
                //声明队列
                channel.basicConsume(queneName, true, new DeliverCallback() {
                    public void handle(String s, Delivery delivery) throws IOException {
                        System.out.println(queneName + "收到的消息是"+new String (delivery.getBody(),"utf-8"));
                    }
                },new CancelCallback () {
                    public void handle(String s1) throws IOException {
                        System.out.println("接受失败");
                    }
                });
                System.out.println(queneName +"开始接受消息");
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } finally {
                // 7 关闭通道
                if (channel!=null && channel.isOpen()) {
                    try {
                        channel.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 8 关闭连接
                if (connection!=null && connection.isOpen()) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };


    public static void main(String[] args) {
        //启动三个消费者(必须有对应的队列才能进行消费,如果队列不存在,则会报异常)
        new Thread(runnable,"queues1").start();
        new Thread(runnable,"queues2").start();
        new Thread(runnable,"queues3").start();
    }

}
