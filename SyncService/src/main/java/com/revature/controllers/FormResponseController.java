package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.FormResponseDto;
import com.revature.models.FormResponse;

/**
 * 
 * This controller handles the creation of a {@link FormResponse} when a survey
 * is successfully filled out by an associate on the front-end.
 *
 */
@RestController
@CrossOrigin
public class FormResponseController {

	public static final String NO_ID_FOUND = "AssociateSurveySessionId provided by JWT does not exist";

	public static final String ALREADY_SUBMITTED = "AssociateSurveySession indicated by JWT was already marked as submitted";

	public static final String INVALID_SURVEY_ID = "SurveyId does not match surveyId within the AssociateSurveySession indicated by JWT";

	public static final String INVALID_JWT = "Could not validate JWT";

	public static final String PERSIST_ERROR = "Error persisting FormResponse";

	/**
	 * This method handles the end point for creating a {@link FormResponse} object.
	 * When this object is created, the corresponding
	 * {@link AssociateSurveyResponse} provided by the JWT is updated to mark
	 * completion. If an error is encountered, an appropriate status code and error
	 * message will be returned.
	 * 
	 * @param token
	 * @param formResponseDto
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/survey/response/{token}")
	public ResponseEntity<String> createFormResponse(@PathVariable("token") String token,
			@RequestBody FormResponseDto formResponseDto) {
		return null;
	}

}