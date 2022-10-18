package rabbitmq.work.fair;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class Work1 {
    public static Runnable runnable = new Runnable() {
        public void run() {
            //公平分发

            // 他这个案例代码多少有点问题,不过重点在指标qos和应答模式改false上,并调用basicAck
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
                connection = connectionFactory.newConnection("消费者");
                channel= connection.createChannel();
                final Channel finalChannel = channel;

                // 指标: qos=1  一次从队列取1条,如果服务器性能够快,则先抢到这1条数据,服务器性能越快抢的越多越快
                finalChannel.basicQos(1);
                //声明队列   公平分发模式需要把应答模式改成false手动应答
                finalChannel.basicConsume(queneName, false, new DeliverCallback() {
                    public void handle(String s, Delivery delivery) throws IOException {
                        System.out.println(queneName + "收到的消息是"+new String (delivery.getBody(),"utf-8"));
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //改成手动应答
                        /*
                         * 手动应答
                         * @param1 deliveryTag 消息应答标记，消息的ID
                         * @param2 multiple:(false、只应答接收到的那个消息 true、应答所有传递过来的消息，批量应答)
                         *         假如有5，6，7，8四个消息被传递过来，当前消息为8
                         *         false时：只会应答8这个消息，5，6，7三个消息不会进行应答
                         *         true时：会将5，6，7，8这四个消息全部应答。
                         */
                        finalChannel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                    }
                },new CancelCallback () {
                    public void handle(String s1) throws IOException {
                        System.out.println("接受失败");
                    }
                });
                System.out.println(queneName +"开始接受消息");
                System.in.read();
            } catch (Exception e) {
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
