package com.revature.service;

import com.revature.dto.SurveyFormDto;
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
	 * Update a {@link SurveyForm} object given a {@link SurveyFormDto} and id to be updated.
	 * @param surveyFormDto The updated {@link SurveyFormDto} object.
	 * @return boolean, true if successful
	 */
	public boolean updateSurveyForm(SurveyForm surveyForm);
    
}
