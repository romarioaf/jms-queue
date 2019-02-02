package br.com.jmsconsumer;

import br.com.jmsconsumer.Consumer;

import javax.jms.JMSException;

public class Application {

    public static void main(String[] args) throws JMSException {

        Consumer jmsConsumer = new Consumer();
        jmsConsumer.create("consumer-app", "personQueue");

        String json1 = jmsConsumer.getMessage(1000, true);
        String json2 = jmsConsumer.getMessage(1000, true);
        String json3 = jmsConsumer.getMessage(1000, true);

        System.out.println(json1);
        System.out.println(json2);
        System.out.println(json3);

    }
}