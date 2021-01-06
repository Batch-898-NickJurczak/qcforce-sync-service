package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.AssociateSurveySession;
import com.revature.repo.AssociateSurveySessionRepo;

/**
 * 
 * Service class for interacting with {@link AssociateSurveySession}
 *
 */
@Service
public class AssociateSurveySessionServiceImpl implements AssociateSurveySessionService {

	private AssociateSurveySessionRepo repo;

	/**
	 * @param repo the AssociateSurveySessionRepo to set
	 */
	@Autowired
	public void setRepo(AssociateSurveySessionRepo repo) {
		this.repo = repo;
	}

	/**
	 * Method for creating {@link AssociateSurveySession}, and should only be called
	 * when creating a JWT. If there is already and existing
	 * {@link AssociateSurveySession} with the same fields as the parameters, then
	 * that object will be returned. If there is an issue persisting a new
	 * {@link AssociateSurveySession}, then null will be returned.
	 * 
	 * @param associateId
	 * @param surveyId
	 * @param batchId
	 * @return associateSurveySessionId
	 */
	@Override
	public AssociateSurveySession createAssociateSurveySession(int associateId, int surveyId, String batchId) {

		return null;

	}

	/**
	 * Method for reading {@link AssociateSurveySession}. If the
	 * {@link AssociateSurveySession} does not exist in the database, null will be
	 * returned.
	 * 
	 * @param associateSurveySessionId
	 * @return {@link AssociateSurveySession}
	 */
	@Override
	public AssociateSurveySession readAssociateSurveySession(int associateSurveySessionId) {

		return null;
	}

	/**
	 * Method for updating {@link AssociateSurveySession}, and should only be called
	 * when receiving a survey submission from the front-end. If the updated
	 * {@link AssociateSurveySession} has invalid fields, then an
	 * {@link AssociateSurveyUpdateException} will be thrown.
	 * 
	 * @param associateSurveySession
	 * @return {@link AssociateSurveySession}
	 */
	@Override
	public AssociateSurveySession updateAssociateSurveySession(AssociateSurveySession associateSurveySession) {

		return null;
	}

}
