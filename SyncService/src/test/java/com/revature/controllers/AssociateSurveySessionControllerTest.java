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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.AssociateSurveySession;
import com.revature.service.AssociateSurveySessionService;

/**
 * 
 * These are tests for the {@link AssociateSurveySessionController}.
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AssociateSurveySessionControllerTest {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private AssociateSurveySessionService service;

	private AssociateSurveySession associateSurveySession;

	private String associateSurveySessionJson;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		associateSurveySession = new AssociateSurveySession(1, 1, 2, "2010", false);

		ObjectMapper om = new ObjectMapper();
		associateSurveySessionJson = om.writeValueAsString(associateSurveySession);
	}

	/**
	 * This tests the readAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if a valid parameter
	 * is given, the controller will return a ResponseEntity with the expected
	 * {@link AssociateSurveySession} object.
	 */
	@Test
	void readAssociateSurveySession_withoutErrors() {
		when(service.readAssociateSurveySession(associateSurveySession.getAssociateSurveySessionId()))
				.thenReturn(associateSurveySession);

		try {
			this.webClient.get().uri("/session/" + associateSurveySession.getAssociateSurveySessionId())
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody()
					.json(associateSurveySessionJson);

		} catch (Exception e) {
			fail("Exception thrown during readAssociateSurveySession_WithoutError: " + e);
		}
	}

	/**
	 * This tests the readAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if a parameter not
	 * matching any persisted {@link AssociateSurveySession} entity is given, the
	 * controller will return a ResponseEntity with a Not Found (404) status code.
	 */
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

	/**
	 * This tests the readAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if a null input is
	 * given, the controller will return a ResponseEntity with a Bad Request (400)
	 * status code.
	 */
	@Test
	void readAssociateSurveySession_nullInput() {
		try {
			this.webClient.get().uri("/session/")
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isBadRequest().expectBody().json("");

		} catch (Exception e) {
			fail("Exception thrown during readAssociateSurveySession_nullInput: " + e);
		}
	}

}
