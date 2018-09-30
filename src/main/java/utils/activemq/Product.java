package utils.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.*;

public class Product {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String URL = "tcp://47.91.218.123:61616";


    @Test
    public void testProductMq() throws JMSException {
        //创建一个ActiveMq的连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, URL);
        //获取连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建session会话
        /** 参数
         * true ：支持事务  fase ： 不支持事务
         * Session.AUTO_ACKNOWLEDGE  如果是事务，就会忽略
         */
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建消息队列
        Queue queue = session.createQueue("hanyh_firsr_actionMq");
        //创建消息生产者
        MessageProducer producer = session.createProducer(queue);

        //可以通过生产者创建消息
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("msa=" + i);
            //往消息队列推送消息
            producer.send(textMessage);
            System.out.println("----------------生产者发送消息：" + textMessage.getText());
        }

        //提交会话
        session.commit();
        //关闭生产者
        producer.close();
        //关闭会话
        session.close();
        //关闭连接
        connection.close();
    }
}
