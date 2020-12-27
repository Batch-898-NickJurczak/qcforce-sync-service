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
	 * Create a {@link SurveyForm} object given a {@link SurveyFortmDto} input from the user.
	 * @param surveyFormDto The {@link SurveyFormDto} object that the user will input.
	 * @return {@link SurveyForm}
	 */
	public SurveyForm createSurveyForm(SurveyForm surveyForm);
}
