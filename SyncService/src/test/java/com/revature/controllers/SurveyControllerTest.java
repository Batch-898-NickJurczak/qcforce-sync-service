package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.service.SurveyService;

import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Tests the {@link QuestionController} and contained methods.
 * 
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SurveyControllerTest {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private SurveyService service;

	private SurveyQuestion surveyQuestion;
	
	private SurveyForm surveyForm;

	private String surveyFormJson;
	
	private Mono<String> monoJson;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		List<String> questions = new ArrayList<String>();
        questions.add("How are you?");
        surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
        
        List<SurveyQuestion> surveyQuestions = new ArrayList<>(1);
        surveyQuestions.add(surveyQuestion);
        
        surveyForm = new SurveyForm(1, "Wezley's Survey", "Wezley Singleton", 
                LocalDateTime.now(), 1, surveyQuestions);

		// writing value as a Json string
		ObjectMapper om = new ObjectMapper();
		surveyFormJson = om.writeValueAsString(new SurveyFormDto(surveyForm));
		monoJson = Mono.just(surveyFormJson);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests the getSurveyForm method of the {@link SurveyController} Ensures that
	 * given a valid surveyForm id, returns the expected {@link SurveyForm}
	 */
	@Test
	void getSurveyFormTest_WithoutError() {

		Mockito.when(service.getSurveyForm(surveyForm.getId())).thenReturn(surveyForm);

		// Test actual method utilizing the webClient
		try {
			this.webClient.get().uri("/survey/" + surveyForm.getId()).accept(MediaType.APPLICATION_JSON)
								.exchange().expectStatus().isOk()
								.expectBody().equals(monoJson);

		} catch (Exception e) {
			fail("Exception thrown during getSurveyFormTest_WithoutError: " + e);
		}
	}

	/**
	 * Tests the getSurveyForm method of the {@link SurveyController} Ensures that
	 * given a valid surveyForm id, if the service returns null, then the controller
	 * returns an empty Json object as well as a NotFound status code.
	 */
	@Test
	void getSurveyFormTest_SurveyNotFound() {

		Mockito.when(service.getSurveyForm(surveyForm.getId())).thenReturn(null);

		// Test actual method utilizing the webClient
		try {
			this.webClient.get().uri("/survey/" + surveyForm.getId()).accept(MediaType.APPLICATION_JSON)
								.exchange().expectStatus().isNotFound()
								.expectBody().equals(null);

		} catch (Exception e) {
			fail("Exception thrown during getSurveyFormTest_SurveyNotFound: " + e);
		}
	}
}
