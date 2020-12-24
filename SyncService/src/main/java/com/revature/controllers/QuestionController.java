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
 * A REST controller that manages all SurveyQuestion related endpoints.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
@RestController
@CrossOrigin
public class QuestionController {
	
	/**
	 * Retrieve a {@link SurveyQuestion} that matches the given id.
	 * @return A promise for a {@link SurveyQuestion} object.
	 */
	@GetMapping("/question/{id}")
	private Mono<SurveyQuestion> getQuestion() {
		
		return null;
	}
	
	@PostMapping
	private Mono<SurveyQuestion> createQuestion(@RequestBody SurveyQuestionDto surveyQuestionDto) {
		return null;
	}
	
}
