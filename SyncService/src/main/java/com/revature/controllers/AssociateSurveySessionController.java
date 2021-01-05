package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AssociateSurveySession;
import com.revature.service.AssociateSurveySessionService;

/**
 * 
 * This controller is used by the front-end service to read an existing
 * {@link AssociateSurveySession} when requested.
 *
 */
@RestController
@CrossOrigin
public class AssociateSurveySessionController {

	AssociateSurveySessionService associateSurveySessionService;

	/**
	 * @param associateSurveySessionService
	 */
	@Autowired
	public void setAssociateSurveySessionService(AssociateSurveySessionService associateSurveySessionService) {
		this.associateSurveySessionService = associateSurveySessionService;
	}

	/**
	 * This method handles the end point for reading an
	 * {@link AssociateSurveySession}. The front-end service hits this end point
	 * using the surveySubId claim provided in the JWT to determine whether a
	 * particular associate from a particular batch has completed a particular
	 * survey.
	 * 
	 * @param associateSurveySessionId
	 * @return ResponseEntity<AssociateSurveySession>
	 */
	@GetMapping("/session/{sessionId}")
	public ResponseEntity<AssociateSurveySession> readAssociateSurveySession(
			@PathVariable("sessionId") int associateSurveySessionId) {

		AssociateSurveySession associateSurveySession = associateSurveySessionService
				.readAssociateSurveySession(associateSurveySessionId);
		ResponseEntity<AssociateSurveySession> re = new ResponseEntity<AssociateSurveySession>(associateSurveySession,
				associateSurveySession == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);

		return re;

	}

}