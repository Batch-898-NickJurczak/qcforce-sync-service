package com.revature.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP.Exchange;

/**
 * RabbitMQ main configurations used throughout the application.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Configuration
public class RabbitMQConfig {

	/**
	 * This {@link String} represents the name of the queue for form messages. 
	 */
	private final String formResponseQueue;
	/**
	 * This {@link String} represents the name of the queue for batch messages. 
	 */
	private final String batchDataQueue;
	/**
	 * This {@link String} represents the key needed to access the form queue. 
	 */
	private final String formResponseRoutingKey ;
	/**
	 * This {@link String} represents the key needed to access the batch queue. 
	 */
	private final String batchDataRoutingKey;
	/**
	 * This {@link String} represents the name of the exchange for forms. 
	 */
	private final String formResponseExchange;
	/**
	 * This {@link String} represents the name of the exchange for batches. 
	 */
	private final String batchDataExchange;

	/**
	 * @param formResponseQueue represents the name of the queue for form messages.
	 * @param batchDataQueue represents the name of the queue for batch messages.
	 * @param formResponseRoutingKey represents the key needed to access the form queue.
	 * @param batchDataRoutingKey represents the key needed to access the batch queue.
	 * @param formResponseExchange represents the name of the exchange for forms.
	 * @param batchDataExchange represents the name of the exchange for batches.
	 */
	public RabbitMQConfig(@Value("${sync-service.rabbitMQ-config.formResponseQueue}") String formResponseQueue,
						  @Value("${sync-service.rabbitMQ-config.batchDataQueue}") String batchDataQueue,
						  @Value("${sync-service.rabbitMQ-config.formResponseRoutingKey}") String formResponseRoutingKey,
						  @Value("${sync-service.rabbitMQ-config.batchDataRoutingKey}") String batchDataRoutingKey,
						  @Value("${sync-service.rabbitMQ-config.formResponseExchange}") String formResponseExchange,
						  @Value("${sync-service.rabbitMQ-config.batchDataExchange}") String batchDataExchange) {
		this.formResponseQueue = formResponseQueue;
		this.batchDataQueue = batchDataQueue;
		this.formResponseRoutingKey = formResponseRoutingKey;
		this.batchDataRoutingKey = batchDataRoutingKey;
		this.formResponseExchange = formResponseExchange;
		this.batchDataExchange = batchDataExchange;
	}


	/**
	 * Creates a {@link Queue} form Forms.
	 * @return a {@link Queue} element and makes a bean for forms.
	 */
	@Bean
	public Queue queueForms() {
		return new Queue(formResponseQueue, true);
	}

	/**
	 * Creates a {@link Queue} for Batches.
	 * @return a {@link Queue} element and makes a bean for batches.
	 */
	@Bean
	public Queue queueBatches() {
		return new Queue(batchDataQueue, true);
	}

	/**
	 * Creates an {@link DirectExchange} for Forms.
	 * @return a {@link DirectExchange} element and makes a bean for forms.
	 */
	@Bean
	public DirectExchange exchangeForms() {
		return new DirectExchange(formResponseExchange);
	}

	/**
	 * Creates an {@link DirectExchange} for Batches.
	 * @return a {@link DirectExchange} element and makes a bean for batches.
	 */
	@Bean
	public DirectExchange exchangeBatches() {
		return new DirectExchange(batchDataExchange);
	}

	/**
	 * Binds a {@link Queue} to an {@link DirectExchange} with the routing key.
	 * @param queueForms represents a {@link Queue} element which will be bound to an exchange.
	 * @param exchangeForms represents the {@link Exchange} a queue will be bound to.
	 * @return a {@link Binding} between a {@link Queue} and an {@link DirectExchange}
	 */
	@Bean
	public Binding bindingForms(Queue queueForms, DirectExchange exchangeForms) {
		return BindingBuilder.bind(queueForms).to(exchangeForms).with(formResponseRoutingKey);
	}

	/**
	 * Binds a {@link Queue} to an {@link DirectExchange} with the routing key.
	 * @param queueBatches represents a {@link Queue} element which will be bound to an exchange.
	 * @param exchangeBatches represents the {@link Exchange} a queue will be bound to.
	 * @return a {@link Binding} between a {@link Queue} and an {@link DirectExchange}
	 */
	@Bean
	public Binding bindingBatches(Queue queueBatches, DirectExchange exchangeBatches) {
		return BindingBuilder.bind(queueBatches).to(exchangeBatches).with(batchDataRoutingKey);
	}

	/**
	 * Creates a {@link MessageConverter} for converting JSON data.
	 * @return a {@link MessageConverter} used to convert object before passing them to a {@link DirectExchange}
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}


	/** Gets the form response queue name
	 * @return form response queue name
	 */
	public String getFormResponseQueue() {
		return formResponseQueue;
	}

	/** Gets the batch data queue name
	 * @return batch data queue name
	 */
	public String getBatchDataQueue() {
		return batchDataQueue;
	}

	/** Gets the form response routing key
	 * @return form response routing key
	 */
	public String getFormResponseRoutingKey() {
		return formResponseRoutingKey;
	}

	/** Gets the batch data routing key
	 * @return batch data routing key
	 */
	public String getBatchDataRoutingKey() {
		return batchDataRoutingKey;
	}

	/** Gets the form response exchange name
	 * @return form response exchange name
	 */
	public String getFormResponseExchange() {
		return formResponseExchange;
	}

	/** Gets the batch data exchange name
	 * @return batch data exchange name
	 */
	public String getBatchDataExchange() {
		return batchDataExchange;
	}
}
