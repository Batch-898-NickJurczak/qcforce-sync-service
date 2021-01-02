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
	
	public AssociateSurveySession findByAssociateIdAndSurveyIdAndBatchId(int associateId, int surveyId, String batchId);

}
