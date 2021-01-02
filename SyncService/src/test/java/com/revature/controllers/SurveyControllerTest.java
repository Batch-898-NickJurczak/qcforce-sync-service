/**
 * 
 */
package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.service.AuthServiceImpl;
import com.revature.service.SurveyService;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Flux;

/**
 * @author Hannah Brett Alma Marc Yarashlee
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SurveyControllerTest {



	@MockBean
	private SurveyService service;
	
	@Autowired
	private WebTestClient webClient;
	
	@MockBean
	private AuthServiceImpl auth;

	private SurveyQuestion surveyQuestion;

	private SurveyForm survey;
	
	private HashMap<String, Object> claim = new HashMap<>();
	
	private ArrayList<Object> list = new ArrayList<>();
	
	private Flux<ArrayList<Object>> fluxList;
	
	

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
		
		claim.put("iat", new Date(System.currentTimeMillis()));
		claim.put("exp", new Date(System.currentTimeMillis() + (1000 * 60 *15)));
		claim.put("surveyId", 1);
		claim.put("batchId", "1");
		claim.put("surveySubId", null);
		

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getSurveyTest_Success() throws JsonProcessingException {
		when(auth.decodeJWT("jwt")).thenReturn(claim);
		when(service.getSurvey((int)claim.get("surveyId"))).thenReturn(survey);
		list.add("success");
		list.add(new SurveyFormDto(survey));
		ObjectMapper im = new ObjectMapper();
        surveyListJson = im.writeValueAsString(list);
        
		try {
			this.webClient.get().uri("/survey/jwt")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody().json(surveyListJson);
			
		}catch(Exception e) {
			fail();
		}
	}
}
