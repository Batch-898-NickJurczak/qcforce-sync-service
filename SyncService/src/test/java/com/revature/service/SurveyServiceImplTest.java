package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.repo.SurveyRepo;

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
	 * Tests the getSurveyForm method of the {@link SurveyServiceImpl}
	 * Ensures that given a valid surveyForm id, returns the expected {@link SurveyForm} object.
	 */
    @Test
	void getSurveyFormTest_WithoutError() {
    	
    	when(repo.getOne(surveyForm.getId())).thenReturn(surveyForm);
    	
    	SurveyForm returned = service.getSurveyForm(surveyForm.getId());
    	
    	verify(repo).getOne(surveyForm.getId());
    	
    	assertEquals(surveyForm, returned, "SurveyServiceImpl.getSurveyForm("+ surveyForm.getId() 
										+") returned mismatched SurveyForm object in "
										+"getSurveyFormTest_WithoutError");	
    }
    
    /**
     * Tests the getSurveyForm method of the {@link SurveyServiceImpl}
     * Ensures that given a valid surveyForm id, if the repo throws an EntityNotFound Exception,
	 * the service will return null.
     */
    @Test
    void getSurveyFormTest_SurveyNotFound() {
    	
    	when(repo.getOne(surveyForm.getId())).thenThrow(EntityNotFoundException.class);
    	
    	SurveyForm returned = service.getSurveyForm(surveyForm.getId());
    	
    	verify(repo).getOne(surveyForm.getId());
    	
    	assertEquals(null, returned, "SurveyServiceImpl.getSurveyForm("+ surveyForm.getId() 
									+") did not return null when repo threw EntityNotFoundException in "
									+"getSurveyFormTest_SurveyNotFound");	
    }
}
