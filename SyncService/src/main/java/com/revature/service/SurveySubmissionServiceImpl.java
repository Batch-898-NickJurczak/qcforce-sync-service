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
		return submissionRepo.updateSurveySubmission(surveySubmission);
	}

	@Override
	public int deleteSurveySubmission(SurveySubmission surveySubmission) {
		return submissionRepo.deleteSurveySubmission(surveySubmission);
	}
	
}
