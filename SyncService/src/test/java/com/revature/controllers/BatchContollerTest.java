package com.revature.controllers;

import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.revature.domain.Batch;
import com.revature.service.MessageService;

//https://josdem.io/techtalk/spring/spring_webflux_web_testing/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BatchContollerTest {

	@MockBean
    MessageService service;
 
    @Autowired
    private WebTestClient webClient;
    
	@Test
	public void testBatchEndPoint() throws Exception {
		webClient.post().uri("/batch") .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(new ArrayList<Batch>())).exchange().expectStatus().isOk();
		Mockito.verify(service, times(1)).sendBatchData(new ArrayList<Batch>());
	}

}
