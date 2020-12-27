package com.revature.service;

import com.revature.models.SurveyForm;

import java.util.List;

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
	 * Retrieves a {@link List} of all {@link SurveyForm}s stored by the repo.
	 * @return {@link List} of {@link SurveyForm}s.
	 */
	@Override
	public List<SurveyForm> getAllSurveyForms() {
		// TODO Auto-generated method stub
		return null;
	}

    
}