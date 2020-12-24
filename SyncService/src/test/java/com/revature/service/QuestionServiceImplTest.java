/**
 * 
 */
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
 * @author Work From Home
 *
 */
@SpringBootTest
class QuestionServiceImplTest {
	
	@Autowired
	private QuestionService service;
	
	@MockBean
	private QuestionRepo repo;
	
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void questionServiceImplTest_WithoutError() {
		
		when(repo.getOne(surveyQuestion.getId())).thenReturn(surveyQuestion);
		
		SurveyQuestion returned = service.getSurveyQuestion(surveyQuestion.getId());
		
		verify(repo).getOne(surveyQuestion.getId());
		
		assertEquals(surveyQuestion, returned, "Object returned does not match expected.");
		
	}
	
	@Test
	void questionServiceImplTest_InvalidInput() {
		
		when(repo.getOne(surveyQuestion.getId())).thenThrow(EntityNotFoundException.class);
		
		SurveyQuestion returned = service.getSurveyQuestion(surveyQuestion.getId());
		
		verify(repo).getOne(surveyQuestion.getId());
		
		assertEquals(null, returned, "Object returned does not match expected.");
		
	}

}
