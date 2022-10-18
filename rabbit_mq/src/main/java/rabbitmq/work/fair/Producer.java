package rabbitmq.work.fair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) {
        // 所有中间件都是基于tcp/ip协议基础之上构建新的协议规范,只不过rabbitmq遵循的是amqp
        // ip 端口

        // 1 创建链接工程 为什么是基于channel去处理而不是连接呢? (connect慢,长链接三次握手四次挥手,channel信道快)
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("119.29.104.21");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("yukicris");
        connectionFactory.setPassword("flzx3000c");
        // 虚拟机的根(可以自行设置,类似oss的文件服务器)
        connectionFactory.setVirtualHost("/");


        Connection connection = null;
        Channel channel = null;
        try {
            // 2 创建链接Connection
             connection = connectionFactory.newConnection("生产者");
            // 3 通过链接获取通道Channel
            channel= connection.createChannel();
            // 4 通过通道创建交换机,队列,绑定关系,路由key,发送消息,接受消息
                //声明队列
            String queueName = "queuel2";
            /**
             * @Params1 队列的名称
             * @Params2 是否要持久化durable false非持久化 true 持久化 (非持久化也会存盘,但是下次服务器重启就没了)
             * @Params3 排他性,是否是独占独立
             * @Params4 是否自动删除,随着最后一个消费者消息完毕消息以后是否会把队列自动删除
             * @Params5 携带附属参数
             */
            channel.queueDeclare(queueName,true,false,false,null);
            // 5 准备消息内容
            String message = "hello yukicirs&kalsit";

            // 补充 5_1 增加交换机,别的一样修改,比如direct_exchange,下面增加个routeKey
            String exchangeName = "fanout-exchange";
            // 补充5_2 定义交换机类型
            String type = "fanout";

            // 补充5_3 定义路由key
            String routeKey = "";
            // 6 发送消息给队列queue  @param1 交换机  @param2 队列、路由key   @param3 消息状态控制  @param4 消息主体
            // 可以存在没有交换机的队列么  不可以,虽然没有指定交换机,他会绑定默认的交换机,默认为direct模式
            //channel.basicPublish("",queueName, null,message.getBytes());
            //补充6_1 指定交换机
            channel.basicPublish(exchangeName,routeKey, null,message.getBytes());
            System.out.println("消息发送成功");

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
}
