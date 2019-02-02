package br.com.jmsconsumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private static String NO_MESSAGE = "no message";

    private String clientId;
    private Connection connection;
    private MessageConsumer messageConsumer;

    public void create(String clientId, String queueName) throws JMSException {
        this.clientId = clientId;

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Destination queue = session.createQueue(queueName);

        messageConsumer = session.createConsumer(queue);

        connection.start();
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }

    public String getMessage(int timeout, boolean acknowledge) throws JMSException {

        String text = NO_MESSAGE;

        Message message = messageConsumer.receive(timeout);

        if (message != null) {
            TextMessage textMessage = (TextMessage) message;

            text = textMessage.getText();

            if (acknowledge) {
                message.acknowledge();
            } else {
                LOGGER.debug(clientId + ": message not acknowledged");
            }

        } else {
            LOGGER.debug(clientId + ": no message received");
        }

        return text;
    }

}