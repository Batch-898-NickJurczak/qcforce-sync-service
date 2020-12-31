package com.revature.controllers;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.SurveySubmissionDto;
import com.revature.models.SurveyForm;
import com.revature.models.SurveySubmission;
import com.revature.service.SurveyServiceImpl;
import com.revature.service.SurveySubmissionService;
import com.revature.service.SurveySubmissionServiceImpl;

import reactor.core.publisher.Mono;

/**
 * Rest Controller for all servey service endpoints
 * 
 * @author Brett Addicott Alma Alva Yara Cruz Hannah Novack
 */

@RestController
public class SurveySubmissionController {

	/**
	 * Creates an instance of {@link SurveySubmissionService} used to send data.
	 */
	private SurveySubmissionServiceImpl surveySubmissionService;
	private SurveyServiceImpl surveyService;

	/**
	 * Initializes all services.
	 *
	 * @param SurveySubmissionService sets from bean of type
	 *                                {@link SurveySubmissionService}.
	 */
	@Autowired
	public void setSurveySubmissionService(SurveySubmissionServiceImpl surveySubmissionService) {
		this.surveySubmissionService = surveySubmissionService;
	}

	/**
	 * Initializes all services.
	 *
	 * @param SurveySubmissionService sets from bean of type
	 *                                {@link SurveySubmissionService}.
	 */
	@Autowired
	public void setSurveyService(SurveyServiceImpl surveyService) {
		this.surveyService = surveyService;
	}

	/**
	 * sets up an end-point for creating a survey submission
	 */
	@PostMapping("/surveysub")
	@ResponseStatus(code = HttpStatus.CREATED)
    public Mono<SurveySubmission> createSurveySubmission(int surveyId, int associateId) {

		SurveySubmissionDto submissionDto = new SurveySubmissionDto();
		
		submissionDto.setSurveyId(surveyId);
		submissionDto.setTakenBy(associateId);
		
		SurveySubmission surveySubmission = submissionDto.toPojo();

		return Mono.just(surveySubmissionService.createSurveySubmission(surveySubmission));
	}

	/**
	 * Sets up an end-point for updating an existing survey submission
	 */
	@PutMapping("/surveysub/{id}")
	public void updateSurveySubmission(@PathParam("surveyId") int surveySubmissionId,
			@RequestBody SurveySubmission surveySubmission) {
		surveySubmissionService.updateSurveySubmission(surveySubmission);

	}

	/**
	 * Sets up an end-point for deleting a survey submission with the provided ID.
	 */
	@DeleteMapping("/surveysub/{id}")
	public void deleteSurveySubmission(@PathParam("surveyId") int surveySubmissionId) {
		SurveySubmission surveySubmission = new SurveySubmission();
		surveySubmission.setSurveySubmissionId(surveySubmissionId);
		surveySubmissionService.deleteSurveySubmission(surveySubmission);
	}

	/**
	 * Sets up an end-point for getting a survey submission with the provided ID.
	 */
	@GetMapping("/surveysub/{id}")
	public SurveySubmission getSurveySubmissionyById(@PathVariable("surveySubId") int surveySubId) {

		return surveySubmissionService.getSurveySubmission(surveySubId);

	}
}
