package com.revature.service;

import com.revature.models.SurveyForm;

/**
 * An interface for handling all the business code related to {@link SurveyForm} objects.
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
public interface SurveyService {
	
	/**
	 * Retrieves a {@link SurveyForm} object that relates to given id.
	 * @param id The id of the SurveyForm object.
	 * @return {@link SurveyForm}
	 */
	public SurveyForm getSurveyForm(int id);
    
}
