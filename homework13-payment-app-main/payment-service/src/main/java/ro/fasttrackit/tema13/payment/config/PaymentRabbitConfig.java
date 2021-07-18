package ro.fasttrackit.tema13.payment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaymentRabbitConfig {
    private final ConnectionFactory connectionFactory;

    @Bean
    FanoutExchange invoiceExchange() {
        return new FanoutExchange("invoice.exchange");
    }

    @Bean
    Queue invoiceQueue() {
        return new AnonymousQueue();
    }

    @Bean
    Binding binding(Queue invoiceQueue, FanoutExchange invoiceExchange) {
        return BindingBuilder.bind(invoiceQueue).to(invoiceExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    FanoutExchange paymentExchange() {
        return new FanoutExchange("payment.exchange");
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
}
