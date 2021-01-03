package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AssociateSurveySession;

/**
 * 
 * This controller handles endpoints for the CRUD operations of {@link AssociateSurveySession}
 *
 */
@RestController
@CrossOrigin
public class AssociateSurveySessionController {

	/**
	 * This method handles the endpoint for creating {@link AssociateSurveySession}
	 * 
	 * @param surveyId
	 * @param associateId
	 * @param batchId
	 * @return ResponseEntity<Integer>
	 */
	@PostMapping("/session")
	public ResponseEntity<Integer> createAssociateSurveySession(@RequestParam int surveyId, @RequestParam int associateId, @RequestParam String batchId) {
		return null;
	}
	
	/**
	 * This method handles the endpoint for reading {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySessionId
	 * @return ResponseEntity<AssociateSurveySession>
	 */
	@GetMapping("/session/{sessionId}")
	public ResponseEntity<AssociateSurveySession> readAssociateSurveySession(@PathVariable("sessionId") int associateSurveySessionId) {
		return null;
	}
	
	/**
	 * This method handles the endpoint for updating {@link AssociateSurveySession}
	 * 
	 * @param associateSurveySession
	 * @return ResponseEntity<AssociateSurveySession>
	 */
	@PutMapping("/session")
	public ResponseEntity<AssociateSurveySession> updateAssociateSurveySession(@RequestBody AssociateSurveySession associateSurveySession) {
		return null;
	}
}
