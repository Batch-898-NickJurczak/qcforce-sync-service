package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.revature.models.AssociateSurveySession;
import com.revature.service.AssociateSurveySessionService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AssociateSurveyControllerTest {

	@Autowired
	WebTestClient webClient;

	@MockBean
	AssociateSurveySessionService service;

	AssociateSurveySession associateSurveySession;

	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);

		associateSurveySession = new AssociateSurveySession(0, 1, 2, "2010", false);

	}

	@Test
	void createAssociateSurveySession_withoutErrors() {
		when(service.createAssociateSurveySession(1, 2, "2010")).thenReturn(associateSurveySession);
		
		try {
    		this.webClient.post().uri(uriBuilder ->
	            uriBuilder.path("/ass")
	            .queryParam("surveyId", 1)
	            .queryParam("associateId", 2)
	            .queryParam("batchId", "2010")
	            .build())
    		.exchange()
    		.expectStatus().isCreated()
    		.expectHeader().valueEquals("Content-Type", "application/json")
    		.expectBody().json(String.valueOf(associateSurveySession.getAssociateSurveySessionId()));
    	}catch (Exception e) {
    		
    		fail("Exception thrown during createSurveyTest_WithoutError: " + e);
    	}
		
	}
	
	@Test
	void createAssociateSurveySession_InputNull() {
		
		try {
    		this.webClient.post().uri("/ass")
    		.exchange()
    		.expectStatus().isBadRequest()
    		.expectHeader().valueEquals("Content-Type", "application/json")
    		.expectBody().returnResult();
    	}catch (Exception e) {
    		
    		fail("Exception thrown during createSurveyTest_InputNull: " + e);
    	}
	}

}
