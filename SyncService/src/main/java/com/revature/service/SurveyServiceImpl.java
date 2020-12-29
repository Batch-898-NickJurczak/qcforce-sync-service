package com.revature.service;

import com.revature.dto.SurveyFormDto;
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
	 * The repository object this service needs to interact with {@link SurveyForm}s stored in the database
	 */
	SurveyRepo surveyRepo;
	
	/**
	 * Set the {@link SurveyRepo} contained within this object.
	 * Normally the Spring framework will set this through Autowiring.
	 * @param surveyRepo
	 */
	@Autowired
	public void setSurveyRepo(SurveyRepo surveyRepo) {
		this.surveyRepo = surveyRepo;
	}

	/**
	 * Update a {@link SurveyForm} object given a {@link SurveyFormDto} and id to be updated.
	 * @param surveyFormDto The updated {@link SurveyFormDto} object.
	 * @return boolean, true if successful
	 */
	@Override
	public boolean updateSurveyForm(SurveyForm surveyForm) {
		
		try{
			surveyRepo.save(surveyForm);
			return true;

		}catch(IllegalArgumentException e){
			return false;
		}
	} 
}