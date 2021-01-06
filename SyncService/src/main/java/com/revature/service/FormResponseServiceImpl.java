package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.AssociateSurveySession;
import com.revature.models.FormResponse;
import com.revature.models.SurveyQuestion;
import com.revature.util.AssociateSurveySessionUpdateException;
import com.revature.util.InvalidAnswersException;
import com.revature.util.InvalidJWTException;
import com.revature.util.InvalidSurveyIdException;

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
	
	private SurveyService surveyService;

	/**
	 * @param surveyService the surveyService to set
	 */
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

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
		if (authService.verifyJWT(token)) {
			
			// Populate questions of formResponse from the appropriate Survey 
			List<SurveyQuestion> surveyQuestions = surveyService.getSurveyForm(
					formResponse.getFormId()).
					getQuestions();
	        List<String> questions = new ArrayList<String>();
	        for (SurveyQuestion surveyQuestion : surveyQuestions) {
	            questions.add(surveyQuestion.getQuestion().get(0));
	        }
	        
	        // Verify the answers match the questions 
	        if (questions.size() != formResponse.getAnswers().size()) {
	        	throw new InvalidAnswersException("Questions and answers are not of same length");
	        }
	        formResponse.setQuestions(questions);
	        
	        // Retrieve claims from token 
			Map<String, Object> claims = authService.getClaim();
			int associateSurveySessionId = (int) claims.get("surveySubId");
			AssociateSurveySession associateSurveySession = associateSurveySessionService
					.readAssociateSurveySession(associateSurveySessionId);
			
			// Check if error occurs while retrieving AssociateSurveySession entity 
			if (associateSurveySession == null) {
				throw new EntityNotFoundException("No corresponding AssociateSurveySession entity found");
			}
			
			// Check if survey Ids do not match up 
			if (associateSurveySession.getSurveyId() != formResponse.getFormId()) {
				throw new InvalidSurveyIdException("Survey ids do not match");
			}
			associateSurveySession.setTaken(true);
			
			// Check for error while updating AssociateSurveySession 
			if (associateSurveySessionService.updateAssociateSurveySession(associateSurveySession) == null) {
				throw new AssociateSurveySessionUpdateException("Error updating AssociateSurveySession");
			}
			messageService.sendSingularFormResponse(formResponse);
			return formResponse;
		}
		// In case verification of token fails 
		throw new InvalidJWTException("Unable to verify JWT");
	}

}
