package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.service.SurveyService;

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
	
	private String surveyFormJson;
	
	private SurveyQuestion surveyQuestion;
	
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
        ObjectMapper om    = new ObjectMapper();
        surveyFormJson = om.writeValueAsString(new SurveyFormDto(surveyForm));

    }

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
    
    /**
     * Add comments to what this tests for each test
     */
    @Test
	void createSurvey_WithoutErrors() {
    	
    }
}
