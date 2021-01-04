package com.revature.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.AssociateSurveySessionServiceImpl;
import com.revature.service.AuthServiceImpl;
import com.revature.service.SurveyService;
import com.revature.dto.SurveyFormDto;
import com.revature.models.AssociateSurveySession;
import com.revature.models.SurveyForm;

import antlr.collections.List;
import io.jsonwebtoken.Claims;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class SurveyController {

	/**
	 * Creates an instance of {@link SurveyService} used to send data.
	 */
	private SurveyService surveyService;

	private AuthServiceImpl authService;
	
	private AssociateSurveySessionServiceImpl assServ;
	
	private AssociateSurveySession associateSurveySess;

	/**
	 * Initializes all services.
	 *
	 * @param SurveyService sets from bean of type {@link surveyService}.
	 */
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@Autowired
	public void setAuthService(AuthServiceImpl authService) {
		this.authService = authService;
	}

	/**
	 * Sets up an end-point for getting a survey with the provided Token.
	 * Verifies if the token recieved is authenticated then checks for conditions to add a 
	 * string and a survey object to be sent to the front end.
	 * 
	 * @return Flux<Object>
	 */
	@GetMapping("/survey/{token}")
	@ResponseStatus(code = HttpStatus.OK)
	public Flux<Object> getSurveyByToken(@PathVariable("token") String token) {
		
		ArrayList<Object> response = new ArrayList<Object>();
		Date date = new Date(System.currentTimeMillis());
		
		if (authService.verifyJWT(token)) {
			
			Map<String, Object> claim = authService.getClaim();
			
			associateSurveySess = assServ.readAssociateSurveySession(((int)claim.get("surveySubId"))) ;
			
			if (date.compareTo((Date) claim.get("exp")) == 1) {
				
				response.add("expired");
				
			} else if (associateSurveySess.isTaken()) {
				
				response.add("completed");
				
			} else {
				
				response.add("success");
				SurveyForm survey = (surveyService.getSurvey((int) claim.get("surveyId")));
				response.add(new SurveyFormDto(survey));
			}

		} else {
			response.add("failure");
		}
		Flux<Object> data = Flux.fromIterable(response);
		return data;
	}
}
