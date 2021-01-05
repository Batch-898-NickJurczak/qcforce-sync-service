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
	
	private Mono<String> monoJson;
	
	private List<SurveyQuestionDto> listOfSurveyQuestionsDto;
	
	private List<SurveyQuestion> listOfSurveyQuestions;
	
	private List<SurveyQuestion> emptyList;
	
	private String surveyQuestionJson;
	
	private String listOfSurveyQuestionsDtoJson;
	
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
		listOfSurveyQuestions.add(surveyQuestion);
		listOfSurveyQuestions.add(surveyQuestion);
		surveyQuestionDto = new SurveyQuestionDto(surveyQuestion);
		listOfSurveyQuestionsDto.add(surveyQuestionDto);
		listOfSurveyQuestionsDto.add(surveyQuestionDto);
		
		
		surveyQuestionDto = new SurveyQuestionDto(surveyQuestion);
		
		// writing value as a Json string
		ObjectMapper om	= new ObjectMapper();
		surveyQuestionJson = om.writeValueAsString(new SurveyQuestionDto(surveyQuestion));
		monoJson = Mono.just(surveyQuestionJson);
		listOfSurveyQuestionsDtoJson = om.writeValueAsString(listOfSurveyQuestionsDto);
		
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
	 * Tests the getSurveyQuestion method of the {@link QuestionController}
	 * Ensures that given a valid surveyQuestion id, returns the expected {@link SurveyQuestion} object.
	 */
	@Test
	void getSurveyQuestionTest_WithoutError() {
		
		Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(surveyQuestion);
		
		// Test actual method utilizing the webClient
		try {			
			this.webClient.get().uri("/question/" + surveyQuestion.getId()).accept(MediaType.APPLICATION_JSON).exchange()
									.expectStatus().isOk()
									.expectBody().json(surveyQuestionJson);
			
		} catch (Exception e) {
			fail("Exception thrown during getSurveyQuestionTest_WithoutError: " + e);
		}
	}
	
	/**
	 * Tests the getSurveyQuestion method of the {@link QuestionController}
	 * Ensures that given a valid surveyQuestion id, if the service returns null, 
	 * then the controller returns an empty Json object as well as a NotFound status code.
	 */
	@Test
	void getSurveyQuestionTest_QuestionNotFound() {
		
		Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(null);
		
		try {					
			this.webClient.get().uri("/question/" + surveyQuestion.getId()).accept(MediaType.APPLICATION_JSON).exchange()
					.expectStatus().isNotFound()
					.expectBody().json("");
			
		} catch (Exception e) {
			fail("Exception thrown during getSurveyQuestionTest_QuestionNotFound: " + e);
		}
	}
	
	/**
	 * Tests the getAllSurveyQuestions method of the {@link QuestionController}
	 * Ensures that it returns the expected list of {@link SurveyQuestion} objects as a JSON.
	 */
	@Test
	void getAllSurveyQuestionsTest_WithoutError() {
		
		Mockito.when(service.getAllSurveyQuestions()).thenReturn(listOfSurveyQuestions);
		
		// Test actual method utilizing the webClient
		try {			
			this.webClient.get().uri("/question").accept(MediaType.APPLICATION_JSON).exchange()
									.expectStatus().isOk()
									.expectBody().json(listOfSurveyQuestionsDtoJson);
			
		} catch (Exception e) {
			fail("Exception thrown during getAllSurveyQuestionsTest_WithoutError: " + e);
		}
	}
	
	/**
	 * Tests the getAllSurveyQuestions method of the {@link QuestionController}
	 * Ensures that it returns an empty Json object, if the service returns an empty list.
	 */
	@Test
	void getAllSurveyQuestionsTest_QuestionsNotFound() {
		
		Mockito.when(service.getAllSurveyQuestions()).thenReturn(emptyList);
		
		try {					
			this.webClient.get().uri("/question").accept(MediaType.APPLICATION_JSON).exchange()
					.expectStatus().isOk()
					.expectBody().json("");
			
		} catch (Exception e) {
			fail("Exception thrown during getAllSurveyQuestionsTest_QuestionsNotFound: " + e);
		}
	}
}
