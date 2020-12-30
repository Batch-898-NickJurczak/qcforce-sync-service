package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.SurveyFormDto;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;
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
     * The service object this controller needs to interact with.
     */
    private SurveyService surveyService;

    /**
     * Set the {@link QuestionService} contained within this object. Normally the
     * Spring framework will set this through Autowiring.
     */
    @Autowired
    public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	
	/**
	 * Create a {@link SurveyForm} that will be inserted into the database.
	 * @param surveyFormDto The user input of {@link SurveyFormDto}.
	 * @return A promise for a newly created {@link SurveyForm} object.
	 */
    @PostMapping("/survey")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<SurveyForm> createSurvey(@RequestBody SurveyFormDto surveyFormDto) {
    	SurveyForm surveyForm = surveyService.createSurveyForm(surveyFormDto.toPojo());
		
		if(surveyForm == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return Mono.just(surveyForm);
    }

	
}
