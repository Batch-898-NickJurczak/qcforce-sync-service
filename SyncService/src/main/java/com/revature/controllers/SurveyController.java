package com.revature.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.SurveyForm;

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
	 * Retrieve a {@link SurveyForm} that matches the given id.
	 * @return A promise for a {@link SurveyForm} object.
	 * @param id The id of the {@link SurveyForm} object to retrieve.
	 */
	@GetMapping("/survey/{id}")
	public Mono<SurveyForm> getQuestion(@PathVariable("id") int id) {
		
		return null;
	}	
}
