package com.revature.controllers;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyFormDto;
import com.revature.dto.SurveyQuestionDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.service.SurveyService;

/**
 * Tests the {@link QuestionController} and contained methods.
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyControllerTest {
    
    @Autowired
    private WebTestClient webClient;
	
	@MockBean
	private SurveyService service;
	
	private SurveyQuestion surveyQuestion;
	
	private SurveyForm surveyForm;
	
	private String surveyFormJson;
	
	private String surveyDtosJson;
	
	private List<SurveyForm> surveyForms;
	
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
		ObjectMapper om	= new ObjectMapper();
		surveyFormJson = om.writeValueAsString(new SurveyFormDto(surveyForm));
		
		// Instantiating attributes for use in getAllSurveyForms();
		List<SurveyFormDto> surveyDtos = new ArrayList<>();
		surveyDtos.add(new SurveyFormDto(surveyForm));
		surveyDtosJson = om.writeValueAsString(surveyDtos);
		
		surveyForms = new ArrayList<>();
		surveyForms.add(surveyForm);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
    
    /**
     * Tests the getAllSurveyForms method of the {@link SurveyController}.
	 * Ensures that given a valid Http request, returns the expected {@link List} of {@link SurveyForm} objects.
     */
    @Test
	void getAllSurveyFormsTest_WithoutError() {
    	
    	Mockito.when(service.getAllSurveyForms()).thenReturn(surveyForms);
		
		// Test actual method utilizing the webClient
		try {			
			this.webClient.get().uri("/survey").accept(MediaType.APPLICATION_JSON).exchange()
									.expectStatus().isOk()
									.expectBody().json(surveyDtosJson);
			
		} catch (Exception e) {
			fail("Exception thrown during getAllSurveyFormsTest_WithoutError: " + e);
		}
		
    }
    
    /**
	 * Tests the getAllSurveyForms method of the {@link SurveyController}.
	 * Ensures that given a valid Http request, if the service returns null, 
	 * then the controller returns an empty Json object as well as a NotFound status code.
	 */
	@Test
	void getAllSurveyFormsTest_SurveysNotFound() {
		
		Mockito.when(service.getAllSurveyForms()).thenReturn(null);
		
		try {					
			this.webClient.get().uri("/survey").accept(MediaType.APPLICATION_JSON).exchange()
					.expectStatus().isNotFound()
					.expectBody().json("");
			
		} catch (Exception e) {
			fail("Exception thrown during getAllSurveyFormsTest_SurveysNotFound: " + e);
		}
	}
}
