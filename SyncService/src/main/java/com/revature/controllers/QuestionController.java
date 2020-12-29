package com.revature.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;
import com.revature.service.QuestionService;

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
	 * Retrieve a {@link SurveyQuestion} that matches the given id.
	 * @return A promise for a {@link SurveyQuestion} object.
	 */
	@GetMapping("/question/{id}")
	public Mono<SurveyQuestion> getQuestion(@PathVariable("id") int id) {
		
		return null;
	}
	

	/**
	 * Create a {@link SurveyQuestion} that will be inserted into the database.
	 * @param surveyQuestionDto The user input of {@link SurveyQuestionDto}
	 * @return A promise for a newly created {@link SurveyQuestion} object.
	 */
	@PostMapping
	public Mono<SurveyQuestion> createQuestion(@RequestBody SurveyQuestionDto surveyQuestionDto) {
		SurveyQuestion surveyQuestion = questionService.createSurveyQuestion(surveyQuestionDto.toPojo());
		
		if(surveyQuestion == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return Mono.just(surveyQuestion);
	}

}
