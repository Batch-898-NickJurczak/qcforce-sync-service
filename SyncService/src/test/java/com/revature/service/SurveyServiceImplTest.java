package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.revature.controllers.SurveyController;
import com.revature.dto.SurveyFormDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
import com.revature.repo.SurveyRepo;

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
	
	private SurveyFormDto surveyFormDto;
	
	private List<SurveyForm> surveyForms;
	
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
		
		List<SurveyQuestion> listOfSurveyQuestions= new ArrayList<>();
        List<String> questions = new ArrayList<>();
        questions.add("How are you?");
        
        surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
        
        listOfSurveyQuestions.add(surveyQuestion);
        listOfSurveyQuestions.add(surveyQuestion);
        
        surveyForm = new SurveyForm(1, "Title", "Creator", LocalDateTime.now(), 1, listOfSurveyQuestions);
        surveyFormDto = new SurveyFormDto(surveyForm);
        
        surveyForms = new ArrayList<>();
		surveyForms.add(surveyForm);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
    
    /**
     * Tests the getAllSurveyForms method of the {@link SurveyService}.
	 * Ensures that given a valid request, returns the expected {@link List} of {@link SurveyForm} objects.
     */
    @Test
	void getAllSurveyFormsTest_WithoutError() {
    	
    	Mockito.when(repo.findAll()).thenReturn(surveyForms);
		
    	List<SurveyForm> returned = service.getAllSurveyForms();
    	
    	verify(repo).findAll();
    	
    	assertEquals(returned, surveyForms, "SurveyServiceImpl.getAllSurveyForms() "
    			+ "returned mismatched SurveyForm object in "
    			+ "getAllSurveyFormsTest_WithoutError.");
    	
    }
    
    /**
     * Tests the getAllSurveyForms method of the {@link SurveyService}
	 * Ensures that given a valid request, if the repo throws an EntityNotFound Exception,
	 * the service will return null.
     */
    @Test
	void getAllSurveyFormsTest_SurveysNotFound() {
    	
    	Mockito.when(repo.findAll()).thenThrow(EntityNotFoundException.class);
		
    	List<SurveyForm> returned = service.getAllSurveyForms();
    	
    	verify(repo).findAll();
    	
    	assertEquals(returned, null, "SurveyServiceImpl.getAllSurveyForms() "
    			+ "did not return null when repo threw EntityNotFoundException "
    			+ "in getAllSurveyFormsTest_SurveysNotFound.");
    	
    }
}
