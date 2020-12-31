package com.revature.service;

import com.revature.models.SurveySubmission;
import com.revature.repo.SurveySubmissionRepo;

public class SurveySubmissionServiceImpl implements SurveySubmissionService {

	SurveySubmissionRepo submissionRepo;

	@Override
	public SurveySubmission getSurveySubmission(int id) {
		return submissionRepo.getSurveySubmission(id);
	}

	@Override
	public SurveySubmission createSurveySubmission(SurveySubmission surveySubmission) {
		return submissionRepo.createSurveySubmission(surveySubmission);
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

	@Override
	public boolean answersNotEmpty(SurveySubmission surveySubmission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean answersMatchQuestions(SurveySubmission surveySubmission) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
