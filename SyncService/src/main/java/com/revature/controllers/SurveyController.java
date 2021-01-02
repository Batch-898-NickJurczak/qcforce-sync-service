package com.revature.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.revature.service.AuthServiceImpl;
import com.revature.service.SurveyService;
import com.revature.dto.SurveyFormDto;

import antlr.collections.List;
import io.jsonwebtoken.Claims;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	public Flux<ArrayList<Object>> getSurveyById(@PathVariable("token") String token) {
		Map<String, Object> claim = authService.decodeJWT(token);
		ArrayList<Object> object = new ArrayList<Object>();
		Date date = new Date(System.currentTimeMillis());
		
		if(date.compareTo((Date)claim.get("exp")) == 1) {
			object.add("expired");
			object.add(null);
		} else if(claim.get("iat") == null) {
			object.add("failure");
			object.add(null);
		} else if (claim.get("surveySubId") != null) {
			object.add("completed");
			object.add(null);
		} else {
			object.add("success");
			object.add(new SurveyFormDto(surveyService.getSurvey((int)claim.get("surveyId"))));
		}
		
		return Flux.just(object);
	}
}

