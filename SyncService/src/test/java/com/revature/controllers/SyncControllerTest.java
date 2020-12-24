package com.revature.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.revature.service.MessageService;

//https://josdem.io/techtalk/spring/spring_webflux_web_testing/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SyncControllerTest {
	
	@MockBean
    MessageService service;
 
    @Autowired
    private WebTestClient webClient;
    
	@Test
	public void testSyncEndPoint() throws Exception {
		webClient.post().uri("/sync").exchange().expectStatus().isOk();
	}

}
