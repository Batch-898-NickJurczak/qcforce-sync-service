package com.revature.service;

import java.util.List;

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
	 * Retrieves a {@link List} of all {@link SurveyForm}s stored by the repo.
	 * @return {@link List} of {@link SurveyForm}s.
	 */
    public List<SurveyForm> getAllSurveyForms();
    
}
