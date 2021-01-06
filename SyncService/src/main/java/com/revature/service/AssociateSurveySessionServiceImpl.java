package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.AssociateSurveySession;
import com.revature.repo.AssociateSurveySessionRepo;
import com.revature.util.AssociateSurveySessionUpdateException;

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

		AssociateSurveySession existingAssociateSurveySession = repo.findByAssociateIdAndSurveyIdAndBatchId(associateId,
				surveyId, batchId);
		if (existingAssociateSurveySession == null) {
			return repo.save(new AssociateSurveySession(1, associateId, surveyId, batchId, false));
		}
		return existingAssociateSurveySession;

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

		try {
			return repo.getOne(associateSurveySessionId);
		} catch (EntityNotFoundException e) {
			return null;
		}

	}

	/**
	 * Method for updating {@link AssociateSurveySession}, and should only be called
	 * when receiving a survey submission from the front-end. If the updated
	 * {@link AssociateSurveySession} has invalid fields, then an
	 * {@link AssociateSurveyUpdateException} will be thrown.
	 * 
	 * @param associateSurveySession
	 * @return {@link AssociateSurveySession}
	 * @throws {@link AssociateSurveySessionUpdateException}
	 */
	@Override
	public AssociateSurveySession updateAssociateSurveySession(AssociateSurveySession associateSurveySession) {

		try {
			AssociateSurveySession existingAssociateSurveySession = repo
					.getOne(associateSurveySession.getAssociateSurveySessionId());
			if (existingAssociateSurveySession.getAssociateId() != associateSurveySession.getAssociateId()
					|| existingAssociateSurveySession.getSurveyId() != associateSurveySession.getSurveyId()
					|| !existingAssociateSurveySession.getBatchId().equals(associateSurveySession.getBatchId())) {
				throw new AssociateSurveySessionUpdateException(
						"Read only fields modified");
			} else if (existingAssociateSurveySession.isTaken()) {
				throw new AssociateSurveySessionUpdateException(
						"exisiting AssociateSurveySession already marked as complete");
			} else if (!associateSurveySession.isTaken()) {
				throw new AssociateSurveySessionUpdateException(
						"Attempting to update non completed AssociateSurveySession");
			}

			return repo.save(associateSurveySession);
		} catch (EntityNotFoundException e) {
			return null;
		}

	}

}
