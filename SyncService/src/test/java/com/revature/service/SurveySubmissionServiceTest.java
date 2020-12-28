package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.revature.domain.Batch;
import com.revature.domain.Employee;
import com.revature.dto.SurveyQuestionDto;
import com.revature.dto.SurveyQuestionResponseDto;
import com.revature.models.QuestionType;
import com.revature.models.Survey;
import com.revature.models.SurveyQuestion;
import com.revature.models.SurveyQuestionResponse;
import com.revature.models.SurveySubmission;
import com.revature.repo.SurveySubmissionRepo;

/**
 * Tests the {@link QuestionServiceImpl} and contained methods.
 * 
 * @authors Hannah and Brett
 */
@SpringBootTest
class SurveySubmissionServiceTest {

	@Autowired
	private SurveySubmissionService service;

	@MockBean
	private SurveySubmissionRepo repo;

	private Survey survey;

	private SurveyQuestion surveyQuestion;

	private SurveyQuestionDto surveyQuestionDto;

	private Batch batch;

	private Employee employee;

	private SurveyQuestionResponse response;

	private SurveyQuestionResponseDto responseDto;

	private SurveySubmission surveySubmission;

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
		List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
		employee = new Employee();
		batch = new Batch();
		batch.setId(1);
		surveyQuestionList.add(surveyQuestion);
		List<String> answer = new ArrayList<String>();
		answer.add("I am not doing so well");
		response = new SurveyQuestionResponse(surveyQuestion, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1,
				                              answer);
		List<SurveyQuestionResponse> responseList = new ArrayList<>();
		responseList.add(response);
		survey = new Survey(1, 1, "General inquiry", 2, LocalDateTime.now(), surveyQuestionList);
		surveySubmission = new SurveySubmission(survey, 1, employee, batch, LocalDateTime.now(), responseList);
	}

	/**
	 * @throws java.lang.Exception
	 */

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests the getSurveySubmission method of the {@link SurveySubmissionService}
	 * Ensures that given a valid surveySubmission id, returns the expected
	 * {@link SurveySubmission} object.
	 */
	@Test
	void getSurveySubmissionTest_WithoutError() {
		when(repo.getSurveySubmission(surveySubmission.getSurveySubmissionId())).thenReturn(surveySubmission);

		SurveySubmission returned = service.getSurveySubmission((surveySubmission.getSurveySubmissionId()));

		verify(repo).getSurveySubmission(surveySubmission.getSurveySubmissionId());

		assertEquals(surveySubmission, returned, "SurveySubmissionServiceImpl.getSurveySubmission(" + surveySubmission.getSurveySubmissionId()
						                                                                            + ") returned mismatched SurveySubmission object in " 
				                                                                                    + "getSurveySubmissionTest_WithoutError");
	}

	/**
	 * Tests the getSurveySubmission method of the {@link SurveySubmissionService}
	 * Ensures that given a valid SurveySubmission id, if the repo throws an
	 * EntityNotFound Exception, the service will return null.
	 */
	@Test
	void getSurveySubmission_SubmissionNotFound() {
		when(repo.getSurveySubmission(surveySubmission.getSurveySubmissionId()))
				.thenThrow(EntityNotFoundException.class);

		SurveySubmission returned = service.getSurveySubmission((surveySubmission.getSurveySubmissionId()));

		verify(repo).getSurveySubmission(surveySubmission.getSurveySubmissionId());

		assertEquals(null, returned, "SurveySubmissionService.getSurveySubmission(" + surveySubmission.getSurveySubmissionId()
					                                                               	+ ") did not return null when repo threw EntityNotFoundException in "
						                                                            + "getSurveySubmission_SubmissionNotFound");
	}

	/**
	 * Tests the answersNotEmpty method of the {@link SurveySubmissionService}
	 * Ensures that a survey submission will only be created if the answers to the
	 * submitted survey are not blank
	 */

	@Test
	void answersNotEmpty_AnswersNotEmpty() {
		boolean test = service.answersNotEmpty(surveySubmission);
		assertEquals(true, test, "Should have returned true for test answersNotEmpty but returned false");
	
	}

	/**
	 * Tests that the function answersNotEmpty returns false if the answers are empty.
	 */
	
	@Test
	void answersNotEmpty_AnswersAreEmpty() {
		responseList.clear();
		boolean test = service.answersAreEmpty(surveySubmission);
		assertEquals(false, test, "Should have returned false for test answersAreEmpty but returned true");
		

	}

	/**
	 * Tests the answersMatchQuestions method of the {@link SurveySubmissionService}
	 * Ensures that the survey submission will only be created if the answer types
	 * also match their corresponding question types
	 */

	@Test
	void answersMatchQuestions_AnswersMatch() {
		
		

	}

	@Test
	void answersMatchQuestion_AnswersDontMatch() {

	}
}
