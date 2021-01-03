package com.revature.service;

import javax.persistence.EntityNotFoundException;

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
	 * Method for creating {@link AssociateSurveySession}
	 * 
	 * @param associateId
	 * @param surveyId
	 * @param batchId
	 * @return associateSurveySessionId
	 */
	@Override
	public AssociateSurveySession createAssociateSurveySession(int associateId, int surveyId, String batchId) {

		AssociateSurveySession existingAssociateSurveySession = repo.findByAssociateIdAndSurveyIdAndBatchId(associateId,
				surveyId, batchId);
		if (existingAssociateSurveySession == null) {
			return repo.save(new AssociateSurveySession(0, associateId, surveyId, batchId, false));
		}
		return existingAssociateSurveySession;

	}

	/**
	 * Method for reading {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySessionId
	 * @return {@link AssociateSurveySession}
	 */
	@Override
	public AssociateSurveySession readAssociateSurveySession(int associateSurveySessionId) {

		try {
			return repo.getOne(associateSurveySessionId);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	/**
	 * Method for updating {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySession
	 * @return {@link AssociateSurveySession}
	 */
	@Override
	public AssociateSurveySession updateAssociateSurveySession(AssociateSurveySession associateSurveySession) {

		try {
			repo.getOne(associateSurveySession.getAssociateSurveySessionId());
			return repo.save(associateSurveySession);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

}
