/**
 * 
 */
package com.revature.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;

import reactor.core.publisher.Mono;

/**
 * @author Work From Home
 *
 */
@RestController
@CrossOrigin
public class QuestionController {
	
	@GetMapping("/question/{id}")
	private Mono<SurveyQuestion> getQuestion() {
		
		return null;
	}
	
	@PostMapping
	private Mono<SurveyQuestion> createQuestion(@RequestBody SurveyQuestionDto surveyQuestionDto) {
		return null;
	}
	
}
