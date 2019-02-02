package br.com.jmsproducer;

import br.com.jmsproducer.Producer;
import br.com.jmsproducer.model.Person;

import javax.jms.JMSException;

public class ProducerApplication {

    public static void main(String[] args) throws JMSException {

        Producer producer = new Producer();
        producer.create("application-1", "personQueue");
        producer.sendName(new Person("Michael", "Jordan", 29));
        producer.sendName(new Person("Airton", "Senna", 31));
        producer.sendName(new Person("Giulia", "Freitas", 0));

    }
}