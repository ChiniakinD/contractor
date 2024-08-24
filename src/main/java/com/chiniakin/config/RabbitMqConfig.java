package com.chiniakin.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RabbitMqConfig {

    public static final String CONTRACTOR_EXCHANGE = "contractors_contractor_exchange";
    public static final String DEALS_CONTRACTOR_QUEUE = "deals_contractor_queue";
    public static final String DEAD_LETTER_EXCHANGE = "deals_dead_exchange";
    public static final String DEAD_LETTER_QUEUE = "deals_contractor_dead_queue";
    public static final String ROUTING_KEY = "contractor.routingKey";
    public static final String DEAD_ROUTING_KEY = "dead.routingKey";
    public static final int RETRY_DELAY_MS = 300000;

    @Bean
    public TopicExchange contractorExchange() {
        return new TopicExchange(CONTRACTOR_EXCHANGE);
    }

    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public Queue dealsContractorQueue() {
        return QueueBuilder.durable(DEALS_CONTRACTOR_QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue dealsContractorDeadQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE)
                .withArgument("x-message-ttl", RETRY_DELAY_MS)
                .withArgument("x-dead-letter-exchange", CONTRACTOR_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding bindingDealsContractorQueue(Queue dealsContractorQueue, TopicExchange contractorExchange) {
        return BindingBuilder.bind(dealsContractorQueue).to(contractorExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeadQueue(Queue dealsContractorDeadQueue, TopicExchange deadLetterExchange) {
        return BindingBuilder.bind(dealsContractorDeadQueue).to(deadLetterExchange).with(DEAD_ROUTING_KEY);
    }

    @Bean
    public PlatformTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }

    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory, PlatformTransactionManager transactionManager) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setTransactionManager(transactionManager);
        return factory;
    }

    @Bean
    public MessageRecoverer messageRecoverer() {
        return new RejectAndDontRequeueRecoverer();
    }

}
