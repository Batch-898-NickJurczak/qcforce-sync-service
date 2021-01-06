package com.revature.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.AssociateSurveySession;
import com.revature.models.FormResponse;

/**
 * This service is used for interacting with the {@link FormResponse}, and
 * updating the {@link AssociateSurveySession} to be taken, when a survey is
 * submitted.
 */
@Service
public class FormResponseServiceImpl implements FormResponseService {

	private AssociateSurveySessionService associateSurveySessionService;

	private AuthServiceImpl authService;
	
	private RabbitMQImpl messageService;

	/**
	 * @param associateSurveySessionService the associateSurveySessionService to set
	 */
	@Autowired
	public void setAssociateSurveySessionService(AssociateSurveySessionService associateSurveySessionService) {
		this.associateSurveySessionService = associateSurveySessionService;
	}

	/**
	 * @param messageService the messageService to set
	 */
	@Autowired
	public void setMessageService(RabbitMQImpl messageService) {
		this.messageService = messageService;
	}

	/**
	 * @param authService the authService to set
	 */
	@Autowired
	public void setAuthService(AuthServiceImpl authService) {
		this.authService = authService;
	}

	/**
	 * This method creates a {@link FormResponse}, and updates the
	 * {@link AssociateSurveySession} that is attached to the token. If the JWT is
	 * invalid, then an InvalidJWTException will be thrown. If the surveySubId
	 * within the token is invalid, then an EntityNotFoundException will be thrown.
	 * If there is an error when updating the {@link AssociateSurveySession}, then
	 * an AssociateSurveySessionUpdateException will be thrown. If the surveyId of
	 * the {@link FormResponse} does not match that of the
	 * {@link AssociateSurveySession}, then an InvalidSurveyIdException will be
	 * thrown.
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
