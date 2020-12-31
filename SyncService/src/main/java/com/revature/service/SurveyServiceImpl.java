package com.revature.service;

import com.revature.models.SurveyForm;
import com.revature.repo.SurveyRepo;

import javax.persistence.EntityNotFoundException;

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
	 * The repository object this service needs to interact with {@link SurveyForm}s
	 * stored in the database.
	 */
	private SurveyRepo surveyRepo;

	/**
	 * Set the {@link SurveyRepo} contained within this object. Normally the Spring
	 * framework will set this through Autowiring.
	 * 
	 * @param surveyRepo
	 */
	@Autowired
	public void setSurveyRepo(SurveyRepo surveyRepo) {
		this.surveyRepo = surveyRepo;
	}

	/**
	 * Create a {@link SurveyForm} object given a {@link SurveyFortmDto} input from
	 * the user.
	 * 
	 * @param surveyFormDto The {@link SurveyFormDto} object that the user will
	 *                      input.
	 * @return {@link SurveyForm}
	 */
	@Override
	public SurveyForm createSurveyForm(SurveyForm surveyForm) {
		SurveyForm returned = null;

		try {

			returned = surveyRepo.save(surveyForm);

		} catch (EntityNotFoundException e) {

			return null;

		}

		return returned;
	}

	@Override
	public boolean updateSurveyForm(SurveyForm surveyForm) {

		try {
			surveyRepo.save(surveyForm);
			return true;

		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	@Override
	public SurveyForm getSurveyForm(int id) {
		
		return surveyRepo.getOne(id);
	}



}
