package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Employee;
import com.revature.models.SurveySubmission;

@Repository
public interface SurveySubmissionRepo extends JpaRepository<Employee, Integer>{

	public SurveySubmission getSurveySubmission(int id);
	
	public SurveySubmission createSurveySubmission(SurveySubmission surveySubmission);
	
	public SurveySubmission updateSurveySubmission(SurveySubmission surveySubmission);
	
	public int deleteSurveySubmission(SurveySubmission surveySubmission);
}
