package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.revature.service.FormResponseService;

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

	private String token = "TOKENSTRING";

	private String formResponseDtoJson;

	private List<String> questions = new ArrayList<String>();

	private List<String> answers = new ArrayList<String>();

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		formResponseDto = new FormResponseDto(1, "12:00:00", 1, questions, answers);

		ObjectMapper om = new ObjectMapper();
		formResponseDtoJson = om.writeValueAsString(formResponseDto);
	}

	@Test
	void testCreateSurveyResponse_withoutErrors() {
		when(service.createFormResponse(Mockito.any(), Mockito.anyString())).thenReturn(formResponseDto.toPojo());

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isCreated()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody().equals(true);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}
	
	@Test
	void testCreateSurveyResponse_withErrors() {
		when(service.createFormResponse(Mockito.any(), Mockito.anyString())).thenReturn(null);

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(formResponseDtoJson)).exchange().expectStatus().isBadRequest()
					.expectHeader().valueEquals("Content-Type", "application/json").expectBody().equals(false);
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

	@Test
	void testCreateSurveyResponse_nullInput() {
		when(service.createFormResponse(formResponseDto.toPojo(), token)).thenReturn(formResponseDto.toPojo());

		try {
			this.webClient.post().uri("/survey/response/" + token).contentType(MediaType.APPLICATION_JSON).exchange()
					.expectStatus().isBadRequest().expectHeader().valueEquals("Content-Type", "application/json")
					.expectBody().returnResult();
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}

	}

}
