package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.fail;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.revature.domain.Batch;
import com.revature.domain.Employee;
import com.revature.dto.SurveyQuestionDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.models.SurveyQuestionResponse;
import com.revature.models.SurveySubmission;
import com.revature.service.SurveySubmissionService;

/**
 * 
 * @author Hannah and Brett
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveySubmissionControllerTest {

	@Autowired
	private WebTestClient webTest;

	@MockBean
	private SurveySubmissionService service;
	
	private SurveyForm survey;
	
	private Batch batch;

	private Employee employee;
	
	private SurveyQuestion surveyQuestion;
	
	private SurveySubmission surveySubmission;
	
	private String surveySubmissionJson;
	
	
	private SurveyQuestionDto surveyQuestionDto;
	
	private SurveyQuestionResponse response;
	
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
	
		//The following block of code sets up a survey with instantiated list of questions
		List<String> questions = new ArrayList<String>();
		questions.add("How are you?");
		surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
		surveyQuestionDto = new SurveyQuestionDto(surveyQuestion);
		List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
		surveyQuestionList.add(surveyQuestion);
		
		//The following block of code sets up a survey submission with an instantiated list of answers 
		//to the questions contained in the initial survey. It is also necessary to instantiate
		//employee and batch objects as they are contained in the survey submission object
		employee = new Employee();
		batch = new Batch();
		batch.setId(1);
		List<String> answer = new ArrayList<String>();
		answer.add("I am not doing so well");
		response = new SurveyQuestionResponse(surveyQuestion, surveySubmission, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1,
				                              answer);
		List<SurveyQuestionResponse> responseList = new ArrayList<>();
		responseList.add(response);
		
		survey = new SurveyForm(1, "Wezley's Survey", "Wezley Singleton", 
                LocalDateTime.now(), 1, surveyQuestionList);

		
		surveySubmission = new SurveySubmission(survey, employee, batch, LocalDateTime.now(), responseList, false);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
	 /**
     * Tests the createSurveySubmission method of the {@link SurveySubmissionController}.
     * Ensures that the given valid {@link SurveySubmissionDto}
     * returns the expected {@link SurveySubmission} object.
     */
    @Test
	void createSurveySubmissionTest_WithoutErrors() {
    	when(service.createSurveySubmission(surveySubmission)).thenReturn(surveySubmission);
    	
    	try {
    		this.webTest.post().uri("/surveysub")
    		.exchange()
    		.expectStatus().isCreated()
    		.expectHeader().valueEquals("Content-Type", "application/json")
    		.expectBody().json(surveySubmissionJson);
    	}catch (Exception e) {
    		
    		fail("Exception thrown during createSurveySubmissionTest_WithoutError: " + e);
    	}
    }
    

    /**
     * Tests the createSurveySubmission method of the {@link SurveySubmissionController}.
     * Ensures that the given null input
     * returns the expected bad request response.
     */
    @Test
	void createSurveySubmissionTest_InputNull() {
    	when(service.createSurveySubmission(surveySubmission)).thenReturn(null);
    	
    	try {
    		this.webTest.post().uri("/surveysub")
    		.exchange()
    		.expectStatus().isBadRequest()
    		.expectHeader().valueEquals("Content-Type", "application/json")
    		.expectBody().json("");
    	}catch (Exception e) {
    		
    		fail("Exception thrown during createSurveySubmissionTest_NullInput: " + e);
    	}
    }
	
	
}
