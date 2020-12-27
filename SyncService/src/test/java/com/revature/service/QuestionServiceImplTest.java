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
	private List<SurveyQuestion> listOfSurveyQuestions;
	private List<SurveyQuestion> emptyList;

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests the getSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestion id, returns the expected {@link SurveyQuestion} object.
	 */
	@Test
	void getSurveyQuestionTest_WithoutError() {
		
		when(repo.getOne(surveyQuestion.getId())).thenReturn(surveyQuestion);
		
		SurveyQuestion returned = service.getSurveyQuestion(surveyQuestion.getId());
		
		verify(repo).getOne(surveyQuestion.getId());
		
		assertEquals(surveyQuestion, returned, "QuestionServiceImpl.getSurveyQuestion("+ surveyQuestion.getId() 
												+") returned mismatched SurveyQuestion object in "
												+"getSurveyQuestionTest_WithoutError");	
	}
	
	/**
	 * Tests the getSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestion id, if the repo throws an EntityNotFound Exception,
	 * the service will return null.
	 */
	@Test
	void getSurveyQuestionTest_QuestionNotFound() {
		
		when(repo.getOne(surveyQuestion.getId())).thenThrow(EntityNotFoundException.class);
		
		SurveyQuestion returned = service.getSurveyQuestion(surveyQuestion.getId());
		
		verify(repo).getOne(surveyQuestion.getId());
		
		assertEquals(null, returned, "QuestionServiceImpl.getSurveyQuestion("+ surveyQuestion.getId() 
									+") did not return null when repo threw EntityNotFoundException in "
									+"getSurveyQuestionTest_QuestionNotFound");	
	}
	
	/**
	 * Tests the getAllSurveyQuestions method of the {@link QuestionServiceImpl}
	 * Ensures that it returns the expected list of {@link SurveyQuestion} objects.
	 */
	@Test
	void getAllSurveyQuestionsTest_WithoutError() {
		
		when(repo.findAll()).thenReturn(listOfSurveyQuestions);
		
		List<SurveyQuestion> returned = service.getAllSurveyQuestions();
		
		verify(repo).findAll();
		
		assertEquals(listOfSurveyQuestions, returned, "QuestionServiceImpl.getAllSurveyQuestions"
												+ " returned mismatched SurveyQuestion object in "
												+"getAllSurveyQuestionsTest_WithoutError");	
	}
	
	/**
	 * Tests the getAllSurveyQuestions method of the {@link QuestionServiceImpl}
	 * Ensures that given an empty List of {@link SurveyQuestion}, the repo returns an empty list.
	 * The service will return an empty list.
	 */
	@Test
	void getAllSurveyQuestionsTest_QuestionsNotFound() {
		
		when(repo.findAll()).thenReturn(emptyList);
		
		List<SurveyQuestion> returned = service.getAllSurveyQuestions();
		
		verify(repo).findAll();
		
		assertEquals(emptyList, returned, "QuestionServiceImpl.getAllSurveyQuestions" 
									+" did not return empty list in getSurveyQuestionTest_QuestionsNotFound");	
	}
}
