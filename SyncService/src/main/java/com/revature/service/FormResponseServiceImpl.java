package com.revature.service;

import org.springframework.stereotype.Service;

import com.revature.models.FormResponse;

/**
 * This service is used for interacting with the {@link FormResponse}, and
 * updating the {@link AssociateSurveySession} to be taken, when a survey is
 * submitted.
 */
@Service
public class FormResponseServiceImpl implements FormResponseService {

	/**
	 * This method creates a {@link FormResponse}, and updates the
	 * {@link AssociateSurveySession} that is attached to the token
	 * 
	 * @param formResponse
	 * @param token
	 * @return FormResponse
	 */
	@Override
	public FormResponse createFormResponse(FormResponse formResponse, String token) {
		return null;
	}

}
