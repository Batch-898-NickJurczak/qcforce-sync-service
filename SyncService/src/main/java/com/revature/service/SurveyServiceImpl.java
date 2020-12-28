package com.revature.service;

import com.revature.models.SurveyForm;
import com.revature.repo.SurveyRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A standard implementation of the {@link SurveyService} interface for handling
 * all the business code related to {@link SurveyForm} objects.
 * 
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@Service
public class SurveyServiceImpl implements SurveyService{
	
	/**
	 * The instance of a dao that we will use to interact with our database
	 */
	private SurveyRepo surveyRepo;
	
	/**
	 * The setter for the {@link SurveyRepo} that will be autowired by default
	 * @param surveyRepo the surveyRepo to set
	 */
	@Autowired
	public void setSurveyRepo(SurveyRepo surveyRepo) {
		this.surveyRepo = surveyRepo;
	}

	/**
	 * Retrieves a {@link List} of all {@link SurveyForm}s stored by the repo.
	 * @return {@link List} of {@link SurveyForm}s.
	 */
	@Override
	public List<SurveyForm> getAllSurveyForms() {
		return surveyRepo.findAll();
	}
	
}