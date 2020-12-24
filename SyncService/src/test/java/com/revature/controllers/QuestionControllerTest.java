/**
 * 
 */
package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyQuestionDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyQuestion;
import com.revature.service.QuestionService;

/**
 * @author Work From Home
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class QuestionControllerTest {
	
    @Autowired
    private WebTestClient webClient;
	
	@MockBean
	private QuestionService service;
	
	private SurveyQuestion surveyQuestion;
	
	private String surveyQuestionJson;
	
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
		
		// writing value as a Json string
		ObjectMapper om	= new ObjectMapper();
		surveyQuestionJson = om.writeValueAsString(new SurveyQuestionDto(surveyQuestion));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void questionControllerTest_WithoutError() {
		
		Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(surveyQuestion);
		
		// expecting request
		try {			
			this.webClient.get().uri("/question/" + surveyQuestion.getId()).accept(MediaType.APPLICATION_JSON).exchange()
									.expectStatus().isOk()
									.expectBody().json(surveyQuestionJson);
			
		} catch (Exception e) {
			
			fail("Exception thrown during call: " + e);
		}
	}
	
	@Test
	void questionControllerTest_InvalidInput() {
		
		Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(null);
		
		try {					
			this.webClient.get().uri("/question/" + surveyQuestion.getId()).accept(MediaType.APPLICATION_JSON).exchange()
					.expectStatus().isBadRequest()
					.expectBody().json("");
			
		} catch (Exception e) {
			
			fail("Exception thrown during call: " + e);
		}
	}
}
