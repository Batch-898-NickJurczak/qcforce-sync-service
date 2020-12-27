package com.revature.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.SurveyFormDto;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;

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
	 * Create a {@link SurveyForm} that will be inserted into the database.
	 * @param surveyFormDto The user input of {@link SurveyFormDto}.
	 * @return A promise for a newly created {@link SurveyForm} object.
	 */
    @PostMapping("/survey")
    public Mono<SurveyForm> createSurvey(@RequestBody SurveyFormDto surveyFormDto) {
    	return null;
    }
}
