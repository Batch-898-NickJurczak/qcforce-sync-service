package com.revature.controllers;

import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.service.SurveyService;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

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
	
	private SurveyForm surveyForm;

	private SurveyFormDto surveyFormDto;
	
	private SurveyQuestion surveyQuestion;

	private List<SurveyQuestion> surveyQuestions;
	
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

        surveyQuestions = new ArrayList<>(1);
        surveyQuestions.add(surveyQuestion);
        
        surveyForm = new SurveyForm(1, "Wezley's Survey", "Wezley Singleton", 
									LocalDateTime.now(), 1, surveyQuestions);

		surveyFormDto = new SurveyFormDto(surveyForm);
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
    
	/**
     * Tests the updateSurvey method of the {@link SurveyController}.
     * Ensures that the given valid {@link SurveyFormDto}
     * returns the expected ok status.
     */
    @Test
	void updateSurveyTest_WithoutError() {

    	when(service.updateSurveyForm(surveyForm)).thenReturn(true);
    	
    	try {
			this.webClient.put().uri("/survey")
			.body(Mono.just(surveyFormDto), SurveyFormDto.class)
			.exchange()
			.expectStatus().isOk();
			
    	}catch (Exception e) {
			fail("Exception thrown during createSurveyTest_WithoutError: " + e);
		}
		
		verify(service).updateSurveyForm(surveyForm);
    }
    
    /**
     * Tests the updateSurvey method of the {@link SurveyController}.
     * Ensures that the given null input
     * returns the expected bad request status.
     */
    @Test
	void updateSurveyTest_NullInput() {
    	when(service.updateSurveyForm(surveyForm)).thenReturn(false);
    	
    	try {
			this.webClient.put().uri("/survey")
			.body(Mono.just(surveyFormDto), SurveyFormDto.class)
    		.exchange()
			.expectStatus().isNotFound();
			
    	}catch (Exception e) {
    		fail("Exception thrown during createSurveyTest_WithoutError: " + e);
		}

		verify(service).updateSurveyForm(surveyForm);
    }
}
