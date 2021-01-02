package com.revature.service;

import org.springframework.stereotype.Service;

import com.revature.models.AssociateSurveySession;

/**
 * 
 * Service class for interacting with {@link AssociateSurveySession}
 *
 */
@Service
public class AssociateSurveySessionService {

	/**
	 * Method for creating {@link AssociateSurveySession}
	 * 
	 * @param associateId
	 * @param surveyId
	 * @param batchId
	 * @return associateSurveySessionId
	 */
	public int createAssociateSurveySession(int associateId, int surveyId, int batchId) {
		return -1;
	}

	/**
	 * Method for reading {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySessionId
	 * @return {@link AssociateSurveySession}
	 */
	public AssociateSurveySession readAssociateSurveySession(int associateSurveySessionId) {
		return null;
	}

	/**
	 * Method for updating {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySession
	 * @return {@link AssociateSurveySession}
	 */
	public AssociateSurveySession updateAssociateSurveySession(AssociateSurveySession associateSurveySession) {
		return null;
	}

}
