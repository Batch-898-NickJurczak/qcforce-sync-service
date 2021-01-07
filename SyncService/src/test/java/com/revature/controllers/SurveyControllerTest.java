package com.revature.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyFormDto;
import com.revature.models.AssociateSurveySession;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.service.AssociateSurveySessionServiceImpl;
import com.revature.service.AuthServiceImpl;
import com.revature.service.SurveyService;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
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

	@MockBean
	private SurveyService service;
	
	@Autowired
	private WebTestClient webClient;
	
	@MockBean
	private AuthServiceImpl auth;
	
	@MockBean
	private AssociateSurveySessionServiceImpl assServ;

	private SurveyQuestion surveyQuestion;

	private SurveyForm survey;
	
	private Map<String, Object> claim = new HashMap<>();
	
	private ArrayList<Object> list = new ArrayList<>();
		
	private AssociateSurveySession assSess;

	private String surveyJson;
	
	private String surveyListJson;

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

    survey = new SurveyForm(1, "Wezley's Survey", "Wezley Singleton", 
            LocalDateTime.now(), 1, surveyQuestions);

    // writing value as a Json string
    ObjectMapper om = new ObjectMapper();
    surveyJson = om.writeValueAsString(new SurveyFormDto(survey));
			
		assSess = new AssociateSurveySession(1, 1, 2, "22", false);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		claim.clear();
	}

	/*
	 * Tests getSurveybyToken in {@link SurveyController}
	 * Check if the returned list of Objects contains a string of "success" along with the {@link SurveyForm} object in the list.
	 */
	@Test
	void getSurveyTest_Success() throws JsonProcessingException {
		
		claim.put("iat", new Date(System.currentTimeMillis()));
		claim.put("exp", new Date(System.currentTimeMillis() + (1000 * 60 *15)));
		claim.put("surveyId", 1);
		claim.put("batchId", "1");
		claim.put("surveySubId", 1);
		
		when(auth.verifyJWT("jwt")).thenReturn(true);
		when(assServ.readAssociateSurveySession((int)claim.get("surveySubId"))).thenReturn(assSess);
		when(auth.getClaim()).thenReturn(claim);
		when(service.getSurveyForm((int)claim.get("surveyId"))).thenReturn(survey);
		
		list.add("success");
		list.add(new SurveyFormDto(survey));
		
		ObjectMapper im = new ObjectMapper();
        surveyListJson = im.writeValueAsString(list);
        
        
		try {
			this.webClient.get().uri("/survey-token")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectBody(Object.class)
			.equals(list);
			
			
		}catch(Exception e) {
			fail("Exception occured in getSurveyTest_Success(): " +e);
		}
	}
	
	/*
	 * Tests getSurveybyToken in {@link SurveyController}
	 * Check if the returned list of Objects contains a string of "failure" along with the null object in the list given that the user is not authenticated.
	 */
	@Test
	void getSurveyTest_Failure() throws JsonProcessingException {
		claim.put("surveyId", 1);
		claim.put("batchId", "1");
		claim.put("surveySubId", 1);
		
		when(auth.verifyJWT("jwt")).thenReturn(false);
		when(service.getSurveyForm((int)claim.get("surveyId"))).thenReturn(survey);
		
		list.add("failure");
		list.add(null);
		
		ObjectMapper im = new ObjectMapper();
        surveyListJson = im.writeValueAsString(list); 
        
		try {
			this.webClient.get().uri("/survey-token")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectBody(Object.class)
			.equals(list);
			
		}catch(NullPointerException e) {
			fail("Exception occured in getSurveyTest_Failure(): " +e);
			}
		}
		
	/*
	 * Tests getSurveybyToken in {@link SurveyController}
	 * Check if the returned list of Objects contains a string of "completed" along with the null object in the list given 
	 * that the user has already taken the survey before.
	 */
		@Test
		void getSurveyTest_Completed() throws JsonProcessingException {
			claim.put("iat", new Date(System.currentTimeMillis()));
			claim.put("exp", new Date(System.currentTimeMillis() + (1000 * 60 *15)));
			claim.put("surveyId", 1);
			claim.put("batchId", "1");
			claim.put("surveySubId", 1);
			
			assSess.setTaken(true);
			
			when(auth.verifyJWT("jwt")).thenReturn(true);
			when(assServ.readAssociateSurveySession((int)claim.get("surveySubId"))).thenReturn(assSess);
			when(auth.getClaim()).thenReturn(claim);
			when(service.getSurveyForm((int)claim.get("surveyId"))).thenReturn(survey);
			
			list.add("completed");
			list.add(null);
			
			ObjectMapper im = new ObjectMapper();
	        surveyListJson = im.writeValueAsString(list);
	        
			try {
				this.webClient.get().uri("/survey-token")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectBody(Object.class)
				.equals(list);
				
			}catch(Exception e) {
				fail("Exception occured in getSurveyTest_Completed(): " +e);
			}
	}
	
		@Test
		void getSurveyTest_Expired() throws JsonProcessingException {
			claim.put("iat", new Date(System.currentTimeMillis()));
			claim.put("exp", new Date(System.currentTimeMillis() - 1));
			claim.put("surveyId", 1);
			claim.put("batchId", "1");
			claim.put("surveySubId", 1);
			
			when(auth.verifyJWT("jwt")).thenReturn(true);
			when(assServ.readAssociateSurveySession((int)claim.get("surveySubId"))).thenReturn(assSess);
			when(auth.getClaim()).thenReturn(claim);
			when(service.getSurveyForm((int)claim.get("surveyId"))).thenReturn(survey);
			
			list.add("expired");
			list.add(null);
			
			ObjectMapper im = new ObjectMapper();
	        surveyListJson = im.writeValueAsString(list);
	        
			try {
				this.webClient.get().uri("/survey-token")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectBody(Object.class)
				.equals(list);
				
			}catch(Exception e) {
				fail("Exception occured in getSurveyTest_Expired(): " +e);
			}
		}

}
