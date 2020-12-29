package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyQuestion;
import com.revature.repo.QuestionRepo;

/**
 * Tests the {@link QuestionServiceImpl} and contained methods.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
@SpringBootTest
class QuestionServiceImplTest {
	
	@Autowired
	private QuestionService service;
	
	@MockBean
	private QuestionRepo repo;
	
	private SurveyQuestion surveyQuestion;
	
	private SurveyQuestionDto surveyQuestionDto;

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}


	/**
	 * Tests the createSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestionDto input, creates and returns the expected {@link SurveyQuestion} object.
	 */
	@Test
	void createSurveyQuestionTest_WithoutError() {
		when(repo.save(surveyQuestion)).thenReturn(surveyQuestion);
		
		SurveyQuestion returned = service.createSurveyQuestion(surveyQuestion);
		
		verify(repo).save(returned);
		
		assertEquals(surveyQuestion, returned, "QuestionServiceImpl.createSurveyQuestion("+ surveyQuestionDto 
											 +") returned mismatched SurveyQuestion object in "
											 +"createSurveyQuestionTest_WithoutError");
		
	}

	/**
	 * Tests the createSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestionDto input, if the repo throws an NullPointer Exception,
	 * the service will return null.
	 */
	@Test
	void createSurveyQuestionTest_NullInput() {
		
		when(repo.save(surveyQuestion)).thenThrow(EntityNotFoundException.class);
		
		SurveyQuestion returned = service.createSurveyQuestion(surveyQuestion);
		
		verify(repo).save(surveyQuestion);
		
		assertEquals(null, returned, "QuestionServiceImpl.createSurveyQuestion("+ surveyQuestionDto 
									 +") did not return null when repo threw EntityNotFound Exception in "
									 +"createSurveyQuestionTest_NullInput");	
		
	}
}
