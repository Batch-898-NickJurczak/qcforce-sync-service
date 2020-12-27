package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;
import com.revature.models.SurveyQuestionResponse;
import com.revature.repo.SurveySubmissionRepo;

/**
 * Tests the {@link QuestionServiceImpl} and contained methods.
 * 
 * @authors Hannah and Brett
 */
@SpringBootTest
class SurveySubmissionServiceTest {

	@Autowired
	private QuestionService service;

	@MockBean
	private SurveySubmissionRepo repo;

	private SurveyQuestion surveyQuestion;

	private SurveyQuestionDto surveyQuestionDto;
	
	private SurveyQuestionResponse response;
	
	private SurveyQuestionResponseDto responseDto;

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
	 * Tests the createSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestionDto input, creates and returns the
	 * expected {@link SurveyQuestion} object.
	 */
	@Test
	void createSurveyQuestionTest_WithoutError() {
		when(repo.save(surveyQuestion)).thenReturn(surveyQuestion);

		SurveyQuestion returned = service.createSurveyQuestion(surveyQuestionDto);

		verify(repo).save(returned);

		assertEquals(surveyQuestion, returned, "QuestionServiceImpl.createSurveyQuestion(" + surveyQuestionDto
				+ ") returned mismatched SurveyQuestion object in " + "createSurveyQuestionTest_WithoutError");

	}

	/**
	 * Tests the createSurveyQuestion method of the {@link QuestionServiceImpl}
	 * Ensures that given a valid surveyQuestionDto input, if the repo throws an IllegalArgumentException Exception,
	 * the service will return null.
	 */
	@Test
	void createSurveyQuestionTest_NullInput() {
		
		when(repo.save(surveyQuestion)).thenThrow(IllegalArgumentException.class);
		
		SurveyQuestion returned = service.createSurveyQuestion(surveyQuestionDto);
		
		verify(repo).save(surveyQuestion);
		
		assertEquals(null, returned, "QuestionServiceImpl.createSurveyQuestion("+ surveyQuestionDto 
									 +") did not return null when repo threw NullPointerException in "
									 +"createSurveyQuestionTest_NullInput");	
		
	}
