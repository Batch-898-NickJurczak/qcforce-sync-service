/**

 * 
 */
package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyDto;
import com.revature.models.QuestionType;
import com.revature.models.Survey;
import com.revature.models.SurveyQuestion;
import com.revature.service.SurveyService;

/**
 * @author Hannah Brett Alma Marc Yarashlee
 *
 */
class SurveyControllerTest {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private SurveyService serviceSurvey;

	private SurveyQuestion surveyQuestion;

	private Survey survey;

	private String surveyJson;

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

		List<SurveyQuestion> surveyQuestionList = new ArrayList<>();

		surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
		
		surveyQuestionList.add(surveyQuestion);

		Survey survey = new Survey(1, 8, "Kubernetes", 30, LocalDateTime.now(), surveyQuestionList);

		ObjectMapper om = new ObjectMapper();

		surveyJson = om.writeValueAsString(new SurveyDto(survey));

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests if the id passed in through the url does not exist in the database
	 * @authors Alma and Yara
	 */
	@Test
	void getSurveyById_SurveyDoesNotExist() {

		Mockito.when(serviceSurvey.getSurvey(survey.getId())).thenReturn(null);

		try {

			this.mvc.perform(get("/survey/" + survey.getId())).andExpect(content().json(""));

		} catch (Exception e) {
			fail("Exception thrown during  get survey by id test " + e);
		}
		
//		Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(null);
//		
//		try {					
//			this.webClient.get().uri("/question/" + surveyQuestion.getId()).accept(MediaType.APPLICATION_JSON).exchange()
//					.expectStatus().isNotFound()
//					.expectBody().json("");
//			
//		} catch (Exception e) {
//			fail("Exception thrown during getSurveyQuestionTest_QuestionNotFound: " + e);
//		}

	}
	

	
	/**
	 * Happy path test: ID passed through path param corresponds with an ID that exists in the database. Should return a
	 * mocked "existing" survey
	 * @authors Brett and Hannah
	 */
	@Test
	void getSurveyById_WithoutErrors() {
		Mockito.when(serviceSurvey.getSurvey(survey.getId())).thenReturn(survey);

		try {

			this.mvc.perform(get("/survey/" + survey.getId())).andExpect(status().isOk())
					.andExpect(content().json(surveyJson));

		} catch (Exception e) {
			fail("Exception thrown during  get survey by id test " + e);
		}
//Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(surveyQuestion);
//		
//		// Test actual method utilizing the webClient
//		try {			
//			this.webClient.get().uri("/question/" + surveyQuestion.getId()).accept(MediaType.APPLICATION_JSON).exchange()
//									.expectStatus().isOk()
//									.expectBody().json(surveyQuestionJson);
//			
//		} catch (Exception e) {
//			fail("Exception thrown during getSurveyQuestionTest_WithoutError: " + e);
//		}

	}
	
	/**
	 * get request (controller)
	(database / tested dao)
	Compare id from the survey with the survey url sent on email
	id is valid ; id number isnt 0

	Example:
	/survey/{id}
	 */
	
	


}
