package com.revature.service;

import com.revature.models.AssociateSurveySession;

public interface AssociateSurveySessionService {

	/**
	 * Method for creating {@link AssociateSurveySession}
	 * 
	 * @param associateId
	 * @param surveyId
	 * @param batchId
	 * @return associateSurveySessionId
	 */
	AssociateSurveySession createAssociateSurveySession(int associateId, int surveyId, String batchId);

	/**
	 * Method for reading {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySessionId
	 * @return {@link AssociateSurveySession}
	 */
	AssociateSurveySession readAssociateSurveySession(int associateSurveySessionId);

	/**
	 * Method for updating {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySession
	 * @return {@link AssociateSurveySession}
	 */
	AssociateSurveySession updateAssociateSurveySession(AssociateSurveySession associateSurveySession);

}