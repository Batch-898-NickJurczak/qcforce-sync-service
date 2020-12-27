package com.revature.service;

import com.revature.models.SurveySubmission;

/**
 * @author Hannah and Brett
 * This class handles survey submission communication between the repo and the controller. Any 
 * business logic regarding the submitted survey goes here
 *
 */
public interface SurveySubmissionService {
	
	public SurveySubmission getSurveySubmission(int id);
	
	public SurveySubmission createSurveySubmission(SurveySubmission surveySubmission);
	
	public SurveySubmission updateSurveySubmission(SurveySubmission surveySubmission);
	
	public int deleteSurveySubmission(SurveySubmission surveySubmission);

}
