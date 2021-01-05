package com.revature.service;

import java.util.List;

import com.revature.models.SurveyQuestion;

/**
 * An interface for handling all the business code related to {@link SurveyQuestion} objects.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
public interface QuestionService {
	
	/**
	 * Retrieves a {@link SurveyQuestion} object that relates to given id.
	 * @param id The id of the SurveyQuestion object.
	 * @return {@link SurveyQuestion}
	 */
	public SurveyQuestion getSurveyQuestion(int id);
	public SurveyQuestion createSurveyQuestion(SurveyQuestion surveyQuestion);
	
	/**
	 * Retrieves a list of {@link SurveyQuestion} objects.
	 * @return A list of {@link SurveyQuestion}.
	 */
	public List<SurveyQuestion> getAllSurveyQuestions();

}
