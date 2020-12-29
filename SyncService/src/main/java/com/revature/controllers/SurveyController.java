package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@Autowired
	SurveyService surveyService;
	
	@PutMapping("/survey")
	public void updateSurvey(@RequestBody SurveyFormDto surveyFormDto){
		
		SurveyForm surveyForm = surveyFormDto.toPojo();
		
		if(!surveyService.updateSurveyForm(surveyForm)){
			throw new ResponseStatusException(HttpStatus.valueOf(404));
		}

		
	}
    
}
