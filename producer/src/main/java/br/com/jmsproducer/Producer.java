package br.com.jmsproducer;

import br.com.jmsproducer.model.Person;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    private String clientId;
    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    public void create(String clientId, String queueName) throws JMSException {
        this.clientId = clientId;

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination queue = session.createQueue(queueName);

        messageProducer = session.createProducer(queue);
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }

    public void sendName(Person personInformation) throws JMSException {
        String text = personInformation.toJson();

        TextMessage textMessage = session.createTextMessage(text);

        messageProducer.send(textMessage);

    }

}