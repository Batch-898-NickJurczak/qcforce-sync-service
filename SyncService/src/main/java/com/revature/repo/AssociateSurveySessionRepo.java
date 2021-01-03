package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.AssociateSurveySession;

/**
 * 
 * JpaRepository for interacting with {@link AssociateSurveySession}
 *
 */
@Repository
public interface AssociateSurveySessionRepo extends JpaRepository<AssociateSurveySession, Integer> {

	/**
	 * This method queries the database for an AssociateSurveySession entity with
	 * matching associateId, surveyId and batchId passed in as parameters. 
	 * 
	 * @param associateId
	 * @param surveyId
	 * @param batchId
	 * @return {@link AssociateSurveySession}
	 */
	public AssociateSurveySession findByAssociateIdAndSurveyIdAndBatchId(int associateId, int surveyId, String batchId);

}
