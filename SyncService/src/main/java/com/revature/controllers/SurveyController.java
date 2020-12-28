package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.SurveyFormDto;
import com.revature.models.SurveyForm;
import com.revature.service.SurveyService;

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
	 * Retrieves a {@link List} of all {@link SurveyForm}s.
	 * @return A promise of {@link SurveyForm}s.
	 */
	@GetMapping("survey")
	public Flux<SurveyFormDto> getAllSurveyForms(HttpServletResponse sr){
		
		List<SurveyForm> surveyForms = surveyService.getAllSurveyForms();
		List<SurveyFormDto> surveyFormsDto = new ArrayList<>();
		
		for (SurveyForm sf : surveyForms) {
			surveyFormsDto.add(new SurveyFormDto(sf));
		}
		
		sr.setStatus(200);
		
		// Fill Flux data with surveyFormsDto list content if it exists. 
		Flux<SurveyFormDto> data = Flux.fromIterable(surveyFormsDto);
		
		return data;
	};
    
}
