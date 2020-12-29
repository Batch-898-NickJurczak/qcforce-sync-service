package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.SurveyFormDto;
import com.revature.models.SurveyForm;
import com.revature.service.SurveyService;

import reactor.core.publisher.Mono;

/**
 * A REST controller that manages all SurveyForm related endpoints.
 * 
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@RestController
@CrossOrigin
public class SurveyController {
	
	/**
	 * The service we use to interact with our business logic. 
	 */
	private SurveyService surveyService;

	/**
	 * The setter for the {@link SurveyService} that will be autowired by default
	 * @param surveyService the surveyService to set 
	 */
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	/**
	 * Retrieve a {@link SurveyForm} that matches the given id.
	 * @return A promise for a {@link Mono}<{@link SurveyFormDto}> object.
	 * @param id The id of the {@link SurveyForm} object to retrieve.
	 */
	@GetMapping("/survey/{id}")
	public Mono<SurveyFormDto> getSurvey(@PathVariable("id") int id) {
		
		SurveyForm surveyForm = surveyService.getSurveyForm(id);
		
		if(surveyForm == null) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		}
		
		SurveyFormDto surveyFormDto = new SurveyFormDto(surveyForm);
				
		return Mono.just(surveyFormDto);
	}	
}
