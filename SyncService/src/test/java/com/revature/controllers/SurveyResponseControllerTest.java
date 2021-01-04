package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.revature.dto.FormResponseDto;
import com.revature.service.FormResponseService;

/**
 * 
 * These are tests for the {@link SurveyResponseController}.
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SurveyResponseControllerTest {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private FormResponseService service;

	private FormResponseDto formResponseDto;

	private String token;

	private List<String> questions = new ArrayList<String>();

	private List<String> answers = new ArrayList<String>();

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		formResponseDto = new FormResponseDto(1, "12:00:00", 1, questions, answers);
	}

	@Test
	void testCreateSurveyResponse_withoutErrors() {
		when(service.createFormResonse(formResponseDto.toPojo(), token)).thenReturn(formResponseDto.toPojo());
		
		try {
			this.webClient.post().uri("/survey/response/"+token).exchange().expectStatus().isBadRequest().expectHeader()
					.valueEquals("Content-Type", "application/json").expectBody().returnResult();
		} catch (Exception e) {

			fail("Exception thrown during createAssociateSurveySession_InputNull: " + e);
		}
	}
	
	@Test
	void testCreateSurveyResponse_nullInput() {
		when(service.createFormResonse(formResponseDto.toPojo(), token)).thenReturn(formResponseDto.toPojo());

	}

}
