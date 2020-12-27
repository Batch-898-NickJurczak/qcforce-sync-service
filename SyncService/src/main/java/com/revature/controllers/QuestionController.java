package com.revature.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Mono<SurveyQuestion> getQuestion(@PathVariable("id") int id) {
		
		return null;
	}
	
	/**
	 * Retrieve a list of {@link SurveyQuestion}.
	 * @return A promise for a list of {@link SurveyQuestion} objects.
	 */
	@GetMapping("/question")
	public Mono<List<SurveyQuestionDto>> getQuestions() {
		return null;
	}
}
