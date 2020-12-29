package com.revature.service;

import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.repo.SurveyRepo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

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

/**
 * Tests the {@link SurveyServiceImpl} and contained methods.
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@SpringBootTest
public class SurveyServiceImplTest {

	@Autowired
	private SurveyService service;
	
	@MockBean
	private SurveyRepo repo;
	
	private SurveyQuestion surveyQuestion;
	
	private SurveyForm surveyForm;
	
	private SurveyFormDto surveyFormDto;
	
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
		
		List<SurveyQuestion> listOfSurveyQuestions = new ArrayList<SurveyQuestion>();
		List<String> questions = new ArrayList<String>();
		
		questions.add("How are you?");
		surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
		listOfSurveyQuestions.add(surveyQuestion);
		listOfSurveyQuestions.add(surveyQuestion);
		
		surveyForm = new SurveyForm(1, "Title", "Creator", LocalDateTime.now(), 1, listOfSurveyQuestions);
		surveyFormDto = new SurveyFormDto(surveyForm);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
    
	/**
	 * Tests the deleteSurveyForm method of the {@link SurveyServiceImpl}
	 * Ensures that given a valid surveyForm id, method returns true.
	 */
    @Test
	void deleteSurveyFormTest_WithoutError() {
    	
    	try {
			boolean returned = service.deleteSurveyForm(surveyForm.getId());

			verify(repo).deleteById(surveyForm.getId());
			assertTrue(returned, "SurveyServiceImpl.deleteSurveyForm(" + surveyForm.getId()
					+ ") did not return true as expected.");

		} catch (EntityNotFoundException e) {
			fail("EntityNotFoundException not caught by SurveyServiceImpl in "
					+ "deleteSurveyFormTest_WithoutError: " + e);
		} catch (Exception e) {
			fail("Exception thrown by SurveyServiceImpl in deleteSurveyFormTest_WithoutError: " + e);
		}
    }
    
    /**
	 * Tests the deleteSurveyForm method of the {@link SurveyServiceImpl}
	 * Ensures that given a valid surveyForm id, if the repo throws an
	 * EntityNotFound Exception, the service will return false.
	 */
    @Test
    void deleteSurveyFormTest_SurveyNotFound() {
    	
    	try {	
			doThrow(IllegalArgumentException.class).when(repo).deleteById(surveyForm.getId());

			boolean returned = service.deleteSurveyForm(surveyForm.getId());

			verify(repo).deleteById(surveyForm.getId());

			assertFalse(returned, "SurveyServiceImpl.deleteSurveyForm(" + surveyForm.getId()
					+ ") did not return false as expected.");
			
		} catch (IllegalArgumentException e) {
			fail("IllegalArgumentException not caught by SurveyServiceImpl in "
					+ "deleteSurveyFormTest_SurveyNotFound: " + e);
		} catch (Exception e) {
			fail("Exception thrown by SurveyServiceImpl in deleteSurveyFormTest_SurveyNotFound: " + e);
		}
    }
}
