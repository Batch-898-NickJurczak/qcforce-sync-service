package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;
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

import reactor.core.publisher.Mono;

/**
 * Tests the {@link QuestionController} and contained methods.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class QuestionControllerTest {
	
    @Autowired
    private WebTestClient webClient;
	
	@MockBean
	private QuestionService service;
	
	private SurveyQuestion surveyQuestion;
	
	private SurveyQuestionDto surveyQuestionDto;
	
	private String surveyQuestionJson;
	
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
		
		surveyQuestionDto = new SurveyQuestionDto(surveyQuestion);
		
		// writing value as a Json string
		ObjectMapper om	= new ObjectMapper();
		surveyQuestionJson = om.writeValueAsString(new SurveyQuestionDto(surveyQuestion));
		monoJson = Mono.just(surveyQuestionJson);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
     * Tests the createSurveyQuestion method of the {@link QuestionController}.
     * Ensures that the given valid {@link SurveyQuestionDto}, 
     * returns the expected {@link SurveryQuestion} object.
     */
    @Test
    void createSurveyQuestionTest_WithoutError() {
        when(service.createSurveyQuestion(surveyQuestion)).thenReturn(surveyQuestion);
        
        try {            
            this.webClient.post().uri("/question")
            .body(Mono.just(surveyQuestionDto), SurveyQuestionDto.class)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody().equals(monoJson);
            
        } catch (Exception e) {
            fail("Exception thrown during createSurveyQuestionTest_WithoutError: " + e);
        }
        
        verify(service).createSurveyQuestion(surveyQuestion);
    }
    
    /**
     * Tests the createSurveyQuestion method of the {@link QuestionController}.
     * Ensures that the given null input, 
     * returns the expected bad request response and empty json.
     */
    @Test
    void createSurveyQuestionTest_InputNull() {
        when(service.createSurveyQuestion(surveyQuestion)).thenReturn(null);
        
        try {            
            this.webClient.post().uri("/question")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody().equals(null);
            
        } catch (Exception e) {
            fail("Exception thrown during createSurveyQuestionTest_WithoutError: " + e);
        }
    }
}
