package io.quarkus.it.artemis;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

@ApplicationScoped
public class ArtemisProducerManager {

    @Inject
    ConnectionFactory connectionFactory;

    public void send(String body) {
        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            JMSProducer producer = context.createProducer();
            producer.send(context.createQueue("test-jms"), body);
        }
    }
}
