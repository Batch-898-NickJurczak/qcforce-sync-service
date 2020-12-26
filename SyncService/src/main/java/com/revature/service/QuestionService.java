package com.revature.service;

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

	/**
	 * Deletes a {@link SurveyQuestion} object that relates to given id.
	 * @param id The id of the SurveyQuestion object.
	 * @return true if successful
	 */
	public boolean deleteSurveyQuestion(int id);
}
