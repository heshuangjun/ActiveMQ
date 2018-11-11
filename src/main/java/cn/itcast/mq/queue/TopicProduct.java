package cn.itcast.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author heshuangjun
 * @date 2018-11-11 15:11
 */
public class TopicProduct {
    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂(连接协议:tcp协议,基于java程序连接activeMQ消息中间件的端口号是:61616)
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.211.55.7:61616");
        //2.获取连接
        Connection connection = factory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session,操作消息中间件的对象(参数一:是否使用事务;参数二:消息通知类型,自动通知)
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5.创建订阅对象(点对点模式,发布订阅模式)
        Topic topic = session.createTopic("topic_test");
        //6.创建消息生产者对象或消费者
        MessageProducer producer = session.createProducer(topic);
        //7.创建消息的具体内容
        TextMessage textMessage = session.createTextMessage("第3条!topic!topic!topic!模式消息");
        //8.使用生成者发送消息
        producer.send(topic,textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();

    }
}
