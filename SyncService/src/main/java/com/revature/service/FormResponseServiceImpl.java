package com.revature.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.AssociateSurveySession;
import com.revature.models.FormResponse;
import com.revature.repo.FormResponseRepo;

/**
 * This service is used for interacting with the {@link FormResponse}, and
 * updating the {@link AssociateSurveySession} to be taken, when a survey is
 * submitted.
 */
@Service
public class FormResponseServiceImpl implements FormResponseService {

	private AssociateSurveySessionService associateSurveySessionService;

	private AuthServiceImpl authService;

	private FormResponseRepo formResponseRepo;

	/**
	 * @param associateSurveySessionService the associateSurveySessionService to set
	 */
	@Autowired
	public void setAssociateSurveySessionService(AssociateSurveySessionService associateSurveySessionService) {
		this.associateSurveySessionService = associateSurveySessionService;
	}

	/**
	 * @param formResponseRepo the formResponseRepo to set
	 */
	@Autowired
	public void setFormResponseRepo(FormResponseRepo formResponseRepo) {
		this.formResponseRepo = formResponseRepo;
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
	 * {@link AssociateSurveySession} that is attached to the token. If any failures
	 * are detected in reading the token or in updating the corresponding
	 * {@link AssociateSurveySession} entity, the {@link FormResponse} will not be
	 * persisted.
	 * 
	 * @param formResponse
	 * @param token
	 * @return FormResponse
	 */
	@Override
	public FormResponse createFormResponse(FormResponse formResponse, String token) {
		if (authService.verifyJWT(token)) {
			Map<String, Object> claims = authService.getClaim();
			int associateSurveySessionId = (int) claims.get("surveySubId");
			AssociateSurveySession associateSurveySession = associateSurveySessionService
					.readAssociateSurveySession(associateSurveySessionId);
			if (associateSurveySession == null) {
				return null;
			}
			associateSurveySession.setTaken(true);
			if (associateSurveySessionService.updateAssociateSurveySession(associateSurveySession) == null) {
				return null;
			}
			return formResponseRepo.save(formResponse);
		}
		return null;
	}

}
