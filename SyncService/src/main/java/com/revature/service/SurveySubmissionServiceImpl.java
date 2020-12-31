package com.revature.service;


import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.revature.models.SurveySubmission;
import com.revature.repo.SurveySubmissionRepo;

/**
 * A standard implementation of the {@link SurveySumbissionService} interface for handling
 * all the business code related to {@link SurveySubmission} objects.
 * 
 * @author 
 * Hannah Novack
 * Brett Addicott
 * Yarashlee cruz
 * Alma Alva
 */
@Service
public class SurveySubmissionServiceImpl implements SurveySubmissionService {

	
	/**
	 * The repository object this service needs to interact with {@link SurveySubmission}
	 * stored in the database.
	 */
	
	private SurveySubmissionRepo submissionRepo;
	
	/**
	 * Set the {@link SurveySubmissionRepo} contained within this object. 
	 * 
	 * @param submissionRepo
	 */
	
	@Autowired
	public void setSurveySubmissionRepo(SurveySubmissionRepo submissionRepo) {
		this.submissionRepo = submissionRepo;
	}

	/**
	 * The following methos are crud operations related to our survey submission object.
	 * 
	 * create, read, update, delete surevy submissions
	 */

	@Override
	public SurveySubmission createSurveySubmission(SurveySubmission surveySubmission) {
		return submissionRepo.createSurveySubmission(surveySubmission);
	}
	
	@Override
	public SurveySubmission getSurveySubmission(int id) {
		return submissionRepo.getSurveySubmission(id);
	}
	
	@Override
	public SurveySubmission updateSurveySubmission(SurveySubmission surveySubmission) {
		//TODO call answersNotEmpty method here
		//TODO call answersMatchQuestions method here
		return submissionRepo.updateSurveySubmission(surveySubmission);
	}

	@Override
	public int deleteSurveySubmission(SurveySubmission surveySubmission) {
		return submissionRepo.deleteSurveySubmission(surveySubmission);
	}

	
	/**
	 * @param SurveySubmission object
	 * @return boolean
	 * returns false if the answers contained in the object are not empty
	 */
	@Override
	public boolean answersNotEmpty(SurveySubmission surveySubmission) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param SurveySubmission object
	 * @return boolean
	 * returns false if the answer types contained in the object do not match the corresponding question types
	 */
	@Override
	public boolean answersMatchQuestions(SurveySubmission surveySubmission) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
