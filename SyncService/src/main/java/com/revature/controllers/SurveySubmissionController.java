package com.revature.controllers;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.models.Survey;
import com.revature.models.SurveySubmission;
import com.revature.service.SurveySubmissionService;
import com.revature.service.SurveySubmissionServiceImpl;

public class SurveySubmissionController {
	/**
	 * Creates an instance of {@link SurveySubmissionService} used to send data.
	 */
	private SurveySubmissionServiceImpl surveySubmissionService;

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
	 * sets up an end-point for creating a survey
	 */
	@PostMapping("/surveysub")
	public SurveySubmission createSurvey(SurveySubmission surveySubmission) {
		return surveySubmissionService.createSurveySubmission(surveySubmission);
	}

	/**
	 * Sets up an end-point for updating an existing survey.
	 */
	@PutMapping("/surveysub/{id}")
	public void updateSurvey(@PathParam("surveyId") int surveySubmissionId, @RequestBody SurveySubmission surveySubmission) {
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
	 * Sets up an end-point for getting a survey with the provided ID.
	 */
	@GetMapping("/survey/{id}")
	public SurveySubmission getSurveSubmissionyById(@PathVariable("surveySubId") int surveySubId) {

		return surveySubmissionService.getSurveySubmission(surveySubId);

	}
}
