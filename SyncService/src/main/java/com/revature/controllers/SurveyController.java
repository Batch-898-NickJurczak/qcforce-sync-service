package com.revature.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.SurveyForm;

import reactor.core.publisher.Flux;

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
	 * Retrieves a {@link List} of all {@link SurveyForm}s.
	 * @return A promise for a {@link List} of {@link SurveyForm}s.
	 */
	@GetMapping("survey")
	public Flux<List<SurveyForm>> getAllSurveyForms(){
		return null;
	};
    
}
