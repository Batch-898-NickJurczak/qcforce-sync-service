package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.AuthServiceImpl;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class AuthController {
	
	private AuthServiceImpl authService;
	
	/*
	 * Setter for the AuthServiceImpl. Is Autowired by Spring.
	 */
	@Autowired
	public void setAuthService(AuthServiceImpl authService) {
		this.authService = authService;
	}


	public AuthController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Endpoint for generating an encrypted JWT Token. Will be used by the SurveyService Service. 
	 */
	@PostMapping("/auth")
	@ResponseBody
	public Mono<String> encryptJWT(@RequestParam int surveyId, String batchId, int surveySubId) {
		
		String jwtToken = authService.createToken(surveyId, batchId, surveySubId);
		
		return Mono.just(jwtToken);
	}

}
