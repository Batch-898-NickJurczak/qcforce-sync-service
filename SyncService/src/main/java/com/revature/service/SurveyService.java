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
	 * Create a {@link SurveyForm} object given a {@link SurveyFortmDto} input from the user.
	 * @param surveyFormDto The {@link SurveyFormDto} object that the user will input.
	 * @return {@link SurveyForm}
	 */
	public SurveyForm createSurveyForm(SurveyForm surveyForm);

	boolean updateSurveyForm(SurveyForm surveyForm);

	SurveyForm getSurveyForm(int id);

	public List<SurveyForm> getAllSurveyForms();

	public void deleteSurvey(SurveyForm survey);
}
