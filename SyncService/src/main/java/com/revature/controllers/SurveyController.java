package com.revature.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.AssociateSurveySessionServiceImpl;
import com.revature.service.AuthServiceImpl;
import com.revature.service.SurveyService;
import com.revature.dto.SurveyFormDto;
import com.revature.models.AssociateSurveySession;
import com.revature.models.SurveyForm;

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
	@GetMapping("survey-token")
	public Flux<Object> getSurveyByToken(@RequestHeader(value = "Authorization") String bearerToken, ServerHttpResponse response) {
		
		String[] splitBearer = bearerToken.split("\\s");
		String token = splitBearer[1];
		
		ArrayList<Object> fluxList = new ArrayList<Object>();
		Date date = new Date(System.currentTimeMillis());
		
		if (authService.verifyJWT(token)) {
			
			Map<String, Object> claim = authService.getClaim();
			
			associateSurveySess = assServ.readAssociateSurveySession(((int)claim.get("surveySubId"))) ;
			
			if (date.compareTo((Date) claim.get("exp")) == 1) {
				
				fluxList.add("expired");
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				
			} else if (associateSurveySess.isTaken()) {
				
				fluxList.add("completed");
				response.setStatusCode(HttpStatus.IM_USED);
				
			} else {
				
				fluxList.add("success");
				SurveyForm survey = (surveyService.getSurveyForm((int) claim.get("surveyId")));
				fluxList.add(new SurveyFormDto(survey));
				response.setStatusCode(HttpStatus.OK);
			}

		} else {
			
			fluxList.add("failure");
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
		}
		
		Flux<Object> data = Flux.fromIterable(fluxList);
		
		return data;
	}
	
}