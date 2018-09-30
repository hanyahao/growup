package utils.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.*;

public class Consumer {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String URL = "tcp://47.91.218.123:61616";


    @Test
    public void testConsumerMq() throws JMSException {
        //创建一个ActiveMq的连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        //获取连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建session会话
        /** 参数
         * true ：支持事务  fase ： 不支持事务
         * Session.AUTO_ACKNOWLEDGE  如果是事务，就会忽略
         */
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        //创建
        Queue queue = session.createQueue("hanyh_firsr_actionMq");
        //创建消息生消费者
        MessageConsumer consumer = session.createConsumer(queue);

        try {
            //开启监听
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费者接收到到的消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            //会让我们的消费者一直处于监听状态
            while (true){}
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭消费者
            consumer.close();
            //关闭会话
            session.close();
            //关闭连接
            connection.close();
        }



    }
}
