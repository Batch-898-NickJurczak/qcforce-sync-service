package com.revature.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.AuthServiceImpl;
import com.revature.service.SurveyService;
import com.revature.dto.SurveyFormDto;
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
	 * Sets up an end-point for getting a survey with the provided ID.
	 * @return 
	 */
	@GetMapping("/survey/{token}")
	@ResponseStatus(code = HttpStatus.OK)
	public Flux<Object> getSurveyById(@PathVariable("token") String token) {
		Map<String, Object> claim = authService.decodeJWT(token);
		ArrayList<Object> response = new ArrayList<Object>();
		Date date = new Date(System.currentTimeMillis());
		
		if(date.compareTo((Date)claim.get("exp")) == 1) {
			response.add("expired");
		} else if(claim.get("iat") == null) {
			response.add("failure");
		} else if (claim.get("surveySubId") != null) {
			response.add("completed");
		} else {
			response.add("success");
			SurveyForm survey = (surveyService.getSurvey((int)claim.get("surveyId")));
			response.add(new SurveyFormDto(survey));
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++" + response);
		Flux<Object> data = Flux.fromIterable(response);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++" + data);
		
		return data;
	}
}

