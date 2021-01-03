package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.AssociateSurveySessionDto;
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

	private AssociateSurveySessionDto associateSurveySessionDto;

	private String associateSurveySessionJson;

	private String associateSurveySessionDtoJson;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		associateSurveySession = new AssociateSurveySession(1, 1, 2, "2010", false);

		associateSurveySessionDto = new AssociateSurveySessionDto(1, 2, "2010");

		ObjectMapper om = new ObjectMapper();
		associateSurveySessionJson = om.writeValueAsString(associateSurveySession);
		associateSurveySessionDtoJson = om.writeValueAsString(associateSurveySessionDto);
	}

	/**
	 * This tests the createAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if valid parameters
	 * are given, the controller will return the expected ResponseEntity.
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	void createAssociateSurveySession_withoutErrors() throws JsonProcessingException {
		when(service.createAssociateSurveySession(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(associateSurveySession);

		try {
			this.webClient.post().uri("/session").contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(associateSurveySessionDtoJson)).accept(MediaType.APPLICATION_JSON)
					.exchange().expectStatus().isCreated().expectHeader()
					.valueEquals("Content-Type", "application/json").expectBody()
					.json(String.valueOf(associateSurveySession.getAssociateSurveySessionId()));
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_WithoutError: " + e);
		}
	}

	/**
	 * This tests the createAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if a null input is
	 * given, the controller will return a ResponseEntity with a Bad Request (400)
	 * status code.
	 */
	@Test
	void createAssociateSurveySession_InputNull() {

		try {
			this.webClient.post().uri("/session").exchange().expectStatus().isBadRequest().expectHeader()
					.valueEquals("Content-Type", "application/json").expectBody().returnResult();
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}
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
		when(service.readAssociateSurveySession(0)).thenReturn(null);

		try {
			this.webClient.get().uri("/session/" + associateSurveySession.getAssociateSurveySessionId())
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isNotFound().expectBody().json("");

		} catch (Exception e) {
			fail("Exception thrown during readAssociateSurveySession_nullInput: " + e);
		}
	}

	/**
	 * This tests the updateAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if a valid parameter
	 * is given, the controller will return a ResponseEntity with the expected
	 * updated {@link AssociateSurveySession} object.
	 */
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

	/**
	 * This tests the updateAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if a null input is
	 * given, the controller will return a ResponseEntity with a Bad Request (400)
	 * status code.
	 */
	@Test
	void updateAssociateSurveySession_nullInput() {
		when(service.updateAssociateSurveySession(associateSurveySession)).thenReturn(null);

		try {
			this.webClient.put().uri("/session").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
					.isBadRequest().expectBody().returnResult();

		} catch (Exception e) {
			fail("Exception thrown during updateAssociateSurveySession_nullInput: " + e);
		}
	}

	/**
	 * This tests the updateAssociateSurveySession method in
	 * {@link AssociateSurveySessionController}. Ensures that if a parameter not
	 * matching any persisted {@link AssociateSurveySession} entity is given, the
	 * controller will return a ResponseEntity with a Bad Request (400) status code.
	 */
	@Test
	void updateAssociateSurveySession_notFound() {

		AssociateSurveySession nonPersistedAssociateSurveySession = new AssociateSurveySession(-1, 0, 0, null, false);
		when(service.updateAssociateSurveySession(nonPersistedAssociateSurveySession)).thenReturn(null);
		try {
			this.webClient.put().uri("/session").body(BodyInserters.fromValue(associateSurveySession))
					.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isBadRequest().expectBody()
					.returnResult();

		} catch (Exception e) {
			fail("Exception thrown during updateAssociateSurveySession_WithoutError: " + e);
		}
	}

}
