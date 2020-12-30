package com.revature.service;

import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.repo.QuestionRepo;
import com.revature.repo.SurveyRepo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

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
	
	private SurveyForm surveyForm;
	
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
		List<SurveyQuestion> listOfSurveyQuestions= new ArrayList<SurveyQuestion>();
		List<String> questions = new ArrayList<String>();
		questions.add("How are you?");
		surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
		listOfSurveyQuestions.add(surveyQuestion);
		listOfSurveyQuestions.add(surveyQuestion);
		surveyForm = new SurveyForm(1, "Title", "Creator", LocalDateTime.now(), 1, listOfSurveyQuestions);
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
    
    /**
     * Tests the createSurveyForm method of the {@link SurveyServiceImpl}
     * Ensures that given a valid {@link surveyFormDto} input, creates and returns the expected {@link SurveyForm} object.
     */
    @Test
	void createSurveyFormTest_WithoutError() {
    	when(repo.save(surveyForm)).thenReturn(surveyForm);
    	
    	SurveyForm returned = service.createSurveyForm(surveyForm);
    	
    	verify(repo).save(returned);
    	
    	assertEquals(surveyForm, returned, "SurveyServiceImpl.createSurveyForm("+ surveyForm
    									+") returned mismatched SurveyForm object in "
    									+"createSurveyFormTest_WithoutError");
    }
    
    /**
     * Tests the createSurveyForm method of the {@link SurveyServiceImpl}
     * Ensures that given a valid {@link surveyFormDto} input, if the repo throws IllegalArguement Exception,
     * the service will return null.
     */
    @Test
	void createSurveyFormTest_NullInput() {
    	when(repo.save(surveyForm)).thenThrow(EntityNotFoundException.class);
    	
    	SurveyForm returned = service.createSurveyForm(surveyForm);
    	
    	verify(repo).save(surveyForm);
    	
    	assertEquals(null, returned, "SurveyServiceImpl.createSurveyForm("+ surveyForm
    									+") did not return null when repo threw EntityNotFound Exception in "
    									+"createSurveyFormTest_NullInput");
    }
}
