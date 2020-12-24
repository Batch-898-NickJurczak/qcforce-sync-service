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
import com.revature.service.SurveyService;

public class SurveyController {
	
	/**
	 * Creates an instance of {@link SurveyService} used to send data.
	 */
	private SurveyService surveyService;
	/**
	 * Initializes all services.
	 *
	 * @param SurveyService sets from bean of type {@link surveyService}.
	 */
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	/**
	 * sets up an end-point for creating a survey
	 */
	@PostMapping("/survey")
	public Survey createSurvey(Survey survey) {
		return surveyService.createSurvey(survey);
	}
	/**
	 * Sets up an end-point for updating an existing survey.
	 */
	@PutMapping("/survey/{id}")
	public void updateSurvey(@PathParam("surveyId") int surveyId, @RequestBody Survey survey) {
		surveyService.updateSurvey(survey);
		
	}
	/**
	 * Sets up an end-point for deleting a survey with the provided ID.
	 */
	@DeleteMapping("/survey/{id}")
	public void deleteSurvey(@PathParam("surveyId") int surveyId) {
		Survey survey = new Survey();
		survey.setId(surveyId);
		surveyService.deleteSurvey(surveyId);
	}
	
	
	/**
	 * Sets up an end-point for getting a survey with the provided ID.
	 */
	@GetMapping("/survey/{id}")
	public Survey getSurveyById(@PathVariable("surveyId") int surveyId) {
		return surveyService.getSurvey(surveyId);
	}
}

