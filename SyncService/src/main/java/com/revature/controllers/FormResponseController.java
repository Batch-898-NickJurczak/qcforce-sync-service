package com.revature.controllers;

import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.FormResponseDto;
import com.revature.models.FormResponse;
import com.revature.service.FormResponseService;
import com.revature.util.AssociateSurveySessionUpdateException;
import com.revature.util.InvalidAnswersException;
import com.revature.util.InvalidJWTException;
import com.revature.util.InvalidSurveyIdException;

/**
 * 
 * This controller handles the creation of a {@link FormResponse} when a survey
 * is successfully filled out by an associate on the front-end.
 *
 */
@RestController
@CrossOrigin
public class FormResponseController {

	private FormResponseService formResponseService;

	/**
	 * @param formResponseService the formResponseService to set
	 */
	@Autowired
	public void setFormResponseService(FormResponseService formResponseService) {
		this.formResponseService = formResponseService;
	}

	public static final String SUCCESS = "Response successfully submitted";

	public static final String NO_ID_FOUND = "AssociateSurveySessionId provided by JWT does not exist";

	public static final String ALREADY_SUBMITTED = "AssociateSurveySession indicated by JWT was already marked as submitted";

	public static final String INVALID_SURVEY_ID = "SurveyId does not match surveyId within the AssociateSurveySession indicated by JWT";

	public static final String INVALID_JWT = "Could not validate JWT";

	public static final String PERSIST_ERROR = "Error persisting FormResponse";

	public static final String QUESTION_ANSWER_ERROR = "The list of answers does not match the list of questions";

	/**
	 * This method handles the end point for creating a {@link FormResponse} object.
	 * The body of the request will contain a {@link FormResponseDto}, and a JWT
	 * should be provided in the header. When this {@link FormResponse} object is
	 * created, the corresponding {@link AssociateSurveySession} provided by the JWT
	 * is updated to mark completion. If an error is encountered, an appropriate
	 * status code and error message will be returned.
	 * 
	 * @param formResponseDto
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/survey/response")
	public ResponseEntity<String> createFormResponse(@RequestBody FormResponseDto formResponseDto,
			HttpServletRequest request) {

		String token = request.getHeader("Authorization").split(" ")[1];

		try {
			FormResponse formResponse = formResponseService.createFormResponse(formResponseDto.toPojo(), token);
			if (formResponse != null) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
			}

		} catch (EntityNotFoundException e) {
			return new ResponseEntity<String>(NO_ID_FOUND, HttpStatus.BAD_REQUEST);
		} catch (AssociateSurveySessionUpdateException e) {
			return new ResponseEntity<String>(ALREADY_SUBMITTED, HttpStatus.BAD_REQUEST);
		} catch (InvalidSurveyIdException e) {
			return new ResponseEntity<String>(INVALID_SURVEY_ID, HttpStatus.BAD_REQUEST);
		} catch (InvalidJWTException e) {
			return new ResponseEntity<String>(INVALID_JWT, HttpStatus.UNAUTHORIZED);
		} catch (InvalidAnswersException e) {
			return new ResponseEntity<String>(QUESTION_ANSWER_ERROR, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(PERSIST_ERROR, HttpStatus.BAD_REQUEST);

	}

}