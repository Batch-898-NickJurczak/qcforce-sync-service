package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.AssociateSurveySessionDto;
import com.revature.models.AssociateSurveySession;
import com.revature.service.AssociateSurveySessionService;

/**
 * 
 * This controller handles endpoints for the CRUD operations of
 * {@link AssociateSurveySession}
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
	 * This method handles the endpoint for creating {@link AssociateSurveySession}
	 * 
	 * @param surveyId
	 * @param associateId
	 * @param batchId
	 * @return ResponseEntity<Integer>
	 */
	@PostMapping("/session")
	public ResponseEntity<Integer> createAssociateSurveySession(
			@RequestBody AssociateSurveySessionDto associateSurveySessionDto) {

		Integer associateSurveySessionId = associateSurveySessionService
				.createAssociateSurveySession(associateSurveySessionDto.getAssociateId(),
						associateSurveySessionDto.getSurveyId(), associateSurveySessionDto.getBatchId())
				.getAssociateSurveySessionId();

		ResponseEntity<Integer> re = new ResponseEntity<Integer>(associateSurveySessionId,
				associateSurveySessionId > 0 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);

		return re;

	}

	/**
	 * This method handles the endpoint for reading {@link AssociateSurveySession}
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

	/**
	 * This method handles the endpoint for updating {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySession
	 * @return ResponseEntity<AssociateSurveySession>
	 */
	@PutMapping("/session")
	public ResponseEntity<AssociateSurveySession> updateAssociateSurveySession(
			@RequestBody AssociateSurveySession associateSurveySession) {

		AssociateSurveySession updatedAssociateSurveySession = associateSurveySessionService
				.updateAssociateSurveySession(associateSurveySession);
		ResponseEntity<AssociateSurveySession> re = new ResponseEntity<AssociateSurveySession>(
				updatedAssociateSurveySession,
				updatedAssociateSurveySession == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
}
