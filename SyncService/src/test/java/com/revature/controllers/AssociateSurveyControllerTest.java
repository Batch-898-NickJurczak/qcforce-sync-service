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
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.AssociateSurveySession;
import com.revature.service.AssociateSurveySessionService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AssociateSurveyControllerTest {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private AssociateSurveySessionService service;

	private AssociateSurveySession associateSurveySession;

	private String associateSurveySessionJson;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		associateSurveySession = new AssociateSurveySession(0, 1, 2, "2010", false);

		ObjectMapper om = new ObjectMapper();
		associateSurveySessionJson = om.writeValueAsString(associateSurveySession);

	}

	@Test
	void createAssociateSurveySession_withoutErrors() {
		when(service.createAssociateSurveySession(1, 2, "2010")).thenReturn(associateSurveySession);

		try {
			this.webClient.post()
					.uri(uriBuilder -> uriBuilder.path("/session").queryParam("surveyId", 1).queryParam("associateId", 2)
							.queryParam("batchId", "2010").build())
					.exchange().expectStatus().isCreated().expectHeader()
					.valueEquals("Content-Type", "application/json").expectBody()
					.json(String.valueOf(associateSurveySession.getAssociateSurveySessionId()));
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_WithoutError: " + e);
		}
	}

	@Test
	void createAssociateSurveySession_InputNull() {

		try {
			this.webClient.post().uri("/session").exchange().expectStatus().isBadRequest().expectHeader()
					.valueEquals("Content-Type", "application/json").expectBody().returnResult();
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}
	}

	@Test
	void readAssociateSurveySession_withoutErrors() {
		when(service.readAssociateSurveySession(0)).thenReturn(associateSurveySession);

		try {
			this.webClient.get().uri("/session/" + associateSurveySession.getAssociateSurveySessionId())
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody()
					.json(associateSurveySessionJson);

		} catch (Exception e) {
			fail("Exception thrown during readAssociateSurveySession_WithoutError: " + e);
		}
	}

	@Test
	void readAssociateSurveySession_notFound() {
		when(service.readAssociateSurveySession(0)).thenReturn(null);

		try {
			this.webClient.get().uri("/session/" + associateSurveySession.getAssociateSurveySessionId())
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isNotFound().expectBody().json("");

		} catch (Exception e) {
			fail("Exception thrown during readAssociateSurveySession_notFound: " + e);
		}
	}

	@Test
	void readAssociateSurveySession_nullInput() {
		when(service.readAssociateSurveySession(0)).thenReturn(null);
		
		try {
			this.webClient.get().uri("/session/" + associateSurveySession.getAssociateSurveySessionId()).accept(MediaType.APPLICATION_JSON)
								.exchange().expectStatus().isBadRequest()
								.expectBody().json("");

		} catch (Exception e) {
			fail("Exception thrown during readAssociateSurveySession_nullInput: " + e);
		}
	}
	
	@Test
	void updateAssociateSurveySession_withoutErrors() {
		when(service.updateAssociateSurveySession(associateSurveySession)).thenReturn(associateSurveySession);
		
		try {
			this.webClient.put().uri("/session").body(BodyInserters.fromValue(associateSurveySession))
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody()
					.json(associateSurveySessionJson);

		} catch (Exception e) {
			fail("Exception thrown during updateAssociateSurveySession_WithoutError: " + e);
		}
	}
	
	@Test
	void updateAssociateSurveySession_nullInput() {
		when(service.updateAssociateSurveySession(associateSurveySession)).thenReturn(null);
		
		try {
			this.webClient.put().uri("/session")
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isBadRequest().expectBody()
					.returnResult();

		} catch (Exception e) {
			fail("Exception thrown during updateAssociateSurveySession_nullInput: " + e);
		}
	}

}
