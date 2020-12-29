package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;
import com.revature.service.QuestionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A REST controller that manages all SurveyQuestion related endpoints.
 * 
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
	 * The service object this controller needs to interact with.
	 */
	private QuestionService questionService;

	/**
	 * Set the {@link QuestionService} contained within this object. Normally the
	 * Spring framework will set this through Autowiring.
	 */
	@Autowired
	public void setQuestionService(QuestionService questionService) {

		this.questionService = questionService;
	}

	/**
	 * Retrieve a {@link Flux}<{@link SurveyQuestionDto}> that matches the given id.
	 * 
	 * @return A promise for a {@link Flux}<{@link SurveyQuestionDto}> object.
	 */
	@GetMapping("/question/{id}")
	public Mono<SurveyQuestionDto> getQuestion(@PathVariable("id") int id) {

		SurveyQuestion question = questionService.getSurveyQuestion(id);

		if(question == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		SurveyQuestionDto questionDto = new SurveyQuestionDto(question);

		return Mono.just(questionDto);
	}
}
