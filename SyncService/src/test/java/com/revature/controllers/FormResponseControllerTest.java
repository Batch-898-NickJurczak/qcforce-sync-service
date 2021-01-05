package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.FormResponseDto;
import com.revature.models.FormResponse;
import com.revature.service.FormResponseService;
import com.revature.util.AssociateSurveySessionUpdateException;
import com.revature.util.InvalidJWTException;
import com.revature.util.InvalidSurveyIdException;

/**
 * 
 * These are tests for the {@link FormResponseController}.
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FormResponseControllerTest {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private FormResponseService service;

	private FormResponseDto formResponseDto;

	private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
			+ "eyJzdXJ2ZXlJZCI6IjEiLCJzdXJ2ZVN1YklkIjoiMiIsImJhdGNoSWQiOiIyMDEwIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYyMzkwMjJ9"
			+ ".2vu-3XIYjH6nhw8yu_KQ3Vz75lG-IedsB_qv7PGdlvM";

	private String formResponseDtoJson;

	private FormResponse formResponse;

	private List<String> questions = new ArrayList<String>();

	private List<String> answers = new ArrayList<String>();

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		formResponseDto = new FormResponseDto(1, "12:00:00", 1, questions, answers);

		formResponse = formResponseDto.toPojo();

		ObjectMapper om = new ObjectMapper();
		formResponseDtoJson = om.writeValueAsString(formResponseDto);
	}

	/**
	 * This tests that if the createFormResponse endpoint is hit with valid
	 * parameters and the {@link FormResponseService} returns a non null
	 * {@link FormResponse} object and a ResponseEntity<Boolean> with value true
	 * will be returned by the controller along with a Created HttpStatus code.
	 */
	@Test
	void testCreateSurveyResponse_withoutErrors() {
		when(service.createFormResponse(formResponse, token)).thenReturn(formResponse);

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isCreated()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody().equals(true);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

	/**
	 * If the createFormResponse endpoint is hit with valid parameters, but the
	 * surveySubId claim within the JWT is not associated with any persisted
	 * {@link AssociateSurveySubmission}, and so the {@link FormResponseService}
	 * throws an EntityNotFoundException, the {@link FormResponseController} will
	 * then return with a BadRequest (400) HttpStatus code, and an informative error
	 * message.
	 */
	@Test
	void testCreateFormResponse_invalidSurveySubId() {
		when(service.createFormResponse(formResponse, token)).thenThrow(EntityNotFoundException.class);

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isBadRequest()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody()
					.equals(FormResponseController.NO_ID_FOUND);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

	/**
	 * If the createFormResponse endpoint is hit with valid parameters, but the
	 * {@link AssociateSurveySubmission} associated with the surveySubId claim
	 * within the JWT is already marked as complete, and so the
	 * {@link FormResponseService} throws an AssociateSurveySessionUpdateException,
	 * the {@link FormResponseController} will then return with a BadRequest (400)
	 * HttpStatus code, and an informative error message.
	 */
	@Test
	void testCreateFormResponse_surveyAlreadySubmitted() {
		when(service.createFormResponse(formResponse, token)).thenThrow(AssociateSurveySessionUpdateException.class);

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isBadRequest()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody()
					.equals(FormResponseController.ALREADY_SUBMITTED);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

	/**
	 * If the createFormResponse endpoint is hit with valid parameters, but the
	 * surveyId within the {@link AssociateSurveySubmission} associated with the
	 * surveySubId claim within the JWT is not equal to the surveyId within the
	 * {@link FormResponseDto}, and so the {@link FormResponseService} throws an
	 * InvalidSurveyIdException, the {@link FormResponseController} will then return
	 * with a BadRequest (400) HttpStatus code, and an informative error message.
	 */
	@Test
	void testCreateFormResponse_mismatchedSurveyIds() {
		when(service.createFormResponse(formResponse, token)).thenThrow(InvalidSurveyIdException.class);

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isBadRequest()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody()
					.equals(FormResponseController.INVALID_SURVEY_ID);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

	/**
	 * If the createFormResponse endpoint is hit with valid parameters, but the
	 * {@link AuthServiceImpl} is not able to validate the JWT, and so the
	 * {@link FormResponseService} throws an InvalidJWTException, the
	 * {@link FormResponseController} will then return with an Unauthorized (401)
	 * HttpStatus code, and an informative error message.
	 */
	@Test
	void testCreateFormResponse_invalidJWT() {
		when(service.createFormResponse(formResponse, token)).thenThrow(InvalidJWTException.class);

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isUnauthorized()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody()
					.equals(FormResponseController.INVALID_JWT);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

	/**
	 * If the createFormResponse endpoint is hit with valid parameters, but the
	 * {@link FormResponseService} encounters an error persisting the
	 * {@link FormResponse}, the {@link FormResponseController} will then return
	 * with an BadRequest (400) HttpStatus code, and an informative error message.
	 */
	@Test
	void testCreateFormResponse_withError() {
		when(service.createFormResponse(formResponse, token)).thenReturn(null);

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isBadRequest()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody()
					.equals(FormResponseController.PERSIST_ERROR);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

	/**
	 * This tests that if the createFormResponse endpoint is hit with null input the
	 * {@link FormResponseController} returns with a BadRequest (400) HttpStatus
	 * code, and an informative error message.
	 */
	@Test
	void testCreateFormResponse_nullInput() {
		try {
			this.webClient.post().uri("/survey/response/").contentType(MediaType.APPLICATION_JSON).exchange()
					.expectStatus().isBadRequest().expectHeader().valueEquals("Content-Type", "application/json")
					.expectBody().returnResult();
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

}
