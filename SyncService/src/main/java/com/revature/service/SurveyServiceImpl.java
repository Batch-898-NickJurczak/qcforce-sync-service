package com.revature.service;

import com.revature.models.SurveyForm;
import com.revature.repo.SurveyRepo;

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
public class SurveyServiceImpl implements SurveyService {

	/**
	 * The repository object this service needs to interact with {@link SurveyForm}s stored in the database.
	 */
	private SurveyRepo surveyRepo;

	/**
	 * Set the {@link SurveyRepo} contained within this object. 
	 * Normally the Spring framework will set this through Autowiring. 
	 */
	@Autowired
	public void setSurveyRepo(SurveyRepo surveyRepo) {
		this.surveyRepo = surveyRepo;
	}

	/**
	 * Retrieves a {@link SurveyForm} object that relates to given id.
	 * @param id The id of the SurveyForm object.
	 * @return {@link SurveyForm}
	 */
	@Override
	public SurveyForm getSurveyForm(int id) {
		
		return surveyRepo.getOne(id);
	}
  
}