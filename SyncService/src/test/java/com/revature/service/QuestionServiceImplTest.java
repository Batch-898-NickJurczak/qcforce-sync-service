package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
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
 * 
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

	/**
	 * Tests the getSurveyQuestion method of the {@link QuestionServiceImpl} Ensures
	 * that given a valid surveyQuestion id, returns the expected
	 * {@link SurveyQuestion} object.
	 */
	@Test
	void getSurveyQuestionTest_WithoutError() {

		when(repo.getOne(surveyQuestion.getId())).thenReturn(surveyQuestion);

		SurveyQuestion returned = service.getSurveyQuestion(surveyQuestion.getId());

		verify(repo).getOne(surveyQuestion.getId());

		assertEquals(surveyQuestion, returned, "QuestionServiceImpl.getSurveyQuestion(" + surveyQuestion.getId()
				+ ") returned mismatched SurveyQuestion object in " + "getSurveyQuestionTest_WithoutError");
	}

	/**
	 * Tests the getSurveyQuestion method of the {@link QuestionServiceImpl} Ensures
	 * that given a valid surveyQuestion id, if the repo throws an EntityNotFound
	 * Exception, the service will return null.
	 */
	@Test
	void getSurveyQuestionTest_QuestionNotFound() {

		when(repo.getOne(surveyQuestion.getId())).thenThrow(EntityNotFoundException.class);

		SurveyQuestion returned = service.getSurveyQuestion(surveyQuestion.getId());

		verify(repo).getOne(surveyQuestion.getId());

		assertEquals(null, returned,
				"QuestionServiceImpl.getSurveyQuestion(" + surveyQuestion.getId()
						+ ") did not return null when repo threw EntityNotFoundException in "
						+ "getSurveyQuestionTest_QuestionNotFound");
	}

	/**
	 * Tests the deleteSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestion id, method returns true.
	 */
	@Test
	void deleteSurveyQuestionTest_WithoutError() {

		try {
			boolean returned = service.deleteSurveyQuestion(surveyQuestion.getId());

			verify(repo).deleteById(surveyQuestion.getId());
			assertTrue(returned, "QuestionServiceImpl.deleteSurveyQuestion(" + surveyQuestion.getId()
					+ ") did not return true as expected.");

		} catch (EntityNotFoundException e) {
			fail("EntityNotFoundException not caught by QuestionServiceImpl in "
					+ "deleteSurveyQuestionTest_WithoutError: " + e);
		} catch (Exception e) {
			fail("Exception thrown by QuestionServiceImpl in deleteSurveyQuestionTest_WithoutError: " + e);
		}
	}

	/**
	 * Tests the deleteSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestion id, if the repo throws an
	 * EntityNotFound Exception, the service will return false.
	 */
	@Test
	void deleteSurveyQuestionTest_QuestionNotFound() {

		try {	
			doThrow(EntityNotFoundException.class).when(repo).deleteById(surveyQuestion.getId());

			boolean returned = service.deleteSurveyQuestion(surveyQuestion.getId());

			verify(repo).deleteById(surveyQuestion.getId());

			assertFalse(returned, "QuestionServiceImpl.deleteSurveyQuestion(" + surveyQuestion.getId()
					+ ") did not return false as expected.");
			
		} catch (EntityNotFoundException e) {
			fail("EntityNotFoundException not caught by QuestionServiceImpl in "
					+ "deleteSurveyQuestionTest_QuestionNotFound: " + e);
		} catch (Exception e) {
			fail("Exception thrown by QuestionServiceImpl in deleteSurveyQuestionTest_QuestionNotFound: " + e);
		}
	}
}
