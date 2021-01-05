package com.revature.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.config.RabbitMQConfig;
import com.revature.domain.Batch;
import com.revature.domain.Form;
import com.revature.models.FormResponse;

/**
 * 
 * These are tests for the {@link RabbitMQImpl}
 *
 */
@ExtendWith(SpringExtension.class)
class RabbitMQImplTest {

	@Mock
	ArrayList<FormResponse> mockArrayList;

	@Mock
	RabbitTemplate rabbitTemplate;

	@Mock
	DataFilterService dataFilterService;

	@Mock
	MessageConverter messageConverter;

	@Mock
	FormService formService;

	@Mock
	RabbitMQConfig rabbitConfig;

	@InjectMocks
	RabbitMQImpl rabbitImpl;

	@Test
	public void testSendFormData() {
		List<FormResponse> fr = new ArrayList<FormResponse>();
		fr.add(new FormResponse());
		when(dataFilterService.mapFormResponses()).thenReturn(fr);
		Form f = new Form();
		f.setFormId(-1);
		when(formService.getFormById(1)).thenReturn(f);
		rabbitImpl.sendData();
	}

	@Test

	public void testSendFormDataInconsistentParameters() {
		List<FormResponse> fr = new ArrayList<FormResponse>();
		fr.add(new FormResponse());
		when(dataFilterService.mapFormResponses()).thenReturn(fr);
		Form f = new Form();
		f.setFormId(1);
		when(formService.getFormById(1)).thenReturn(f);
		rabbitImpl.sendData();
	}

	@Test
	public void testSendFormDataConvertAndSend() {
		when(dataFilterService.mapFormResponses()).thenReturn(null);
		Form f = new Form();
		f.setFormId(-1);
		rabbitImpl.sendData();
	}

	@Test
	public void testBatchFormData() {
		List<Batch> bd = new ArrayList<Batch>();
		bd.add(new Batch());
		rabbitImpl.sendBatchData(bd);
	}

	/**
	 * This tests the sendSingularFormResponse method of the {@link RabbitMQImpl}.
	 * Ensures that a singular {@link FormResponse} can be sent to the queue without
	 * error.
	 */
	@Test
	public void testSendSingularFormResponse() {
		FormResponse formResponse = new FormResponse();
		rabbitImpl.sendSingularFormResponse(formResponse);
	}

}