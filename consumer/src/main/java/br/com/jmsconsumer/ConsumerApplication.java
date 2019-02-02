package br.com.jmsconsumer;

import br.com.jmsconsumer.Consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ConsumerApplication {

    public static void main(String[] args) throws JMSException {

        Consumer jmsConsumer = new Consumer();
        jmsConsumer.create("consumer-app", "personQueue");

        System.out.println(jmsConsumer.getMessage(1000, true));
        System.out.println(jmsConsumer.getMessage(1000, true));
        System.out.println(jmsConsumer.getMessage(1000, true));

    }
}