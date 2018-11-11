package cn.itcast.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author heshuangjun
 * @date 2018-11-11 15:27
 */
public class TopicConsumer {
    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂(连接协议:tcp协议,基于java程序连接activeMQ消息中间件的端口号是:61616)
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.211.55.7:61616");
        //2.获取连接
        Connection connection = factory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session,操作消息中间件的对象(参数一:是否使用事务;参数二:消息通知类型,自动通知)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象(点对点模式,发布订阅模式)
        Topic topic = session.createTopic("topic_test");


        //6.创建消息消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7.消费消息的具体内容
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //消费消息
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        while (true) {
        }
        /*//9.关闭资源
        consumer.close();
        session.close();
        connection.close();*/
    }
}
