package server;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.spi.LoggingEvent;

import javax.jms.*;

/**
 * Author:Shawn.Xu
 * Date:2016/8/31
 * Description:
 */
public class Server implements MessageListener {
    public Server() throws Exception {
        // create consumer and listen queue
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        //////////////注意这里JMSAppender只支持TopicDestination，下面会说到////////////////
        Destination topicDestination = session.createTopic("logTopic");
        MessageConsumer consumer = session.createConsumer(topicDestination);
        consumer.setMessageListener(this);


      /*  // clean up
        Thread.sleep(1000);
        consumer.close();
        session.close();
        connection.close();
        System.exit(1);*/
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }

    public void onMessage(Message message) {
        try {
            // receive log event in your consumer
            LoggingEvent event = (LoggingEvent)((ActiveMQObjectMessage)message).getObject();
            System.out.println("Received log [" + event.getLevel() + "]: "+ event.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
