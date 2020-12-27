package com.revature.service;

/**
 * An interface for handling all the business code related to {@link SurveyForm}
 * objects.
 * 
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
public interface SurveyService {

	/**
	 * Deletes a {@link SurveyForm} object that relates to given id.
	 * 
	 * @param id The id of the SurveyForm object.
	 * @return True if successful
	 */
	public boolean deleteSurveyForm(int id);

}
