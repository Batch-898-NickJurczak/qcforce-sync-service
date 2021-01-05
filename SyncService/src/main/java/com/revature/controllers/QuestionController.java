package com.revature.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;
import com.revature.service.QuestionService;
import com.revature.service.QuestionServiceImpl;

import reactor.core.publisher.Flux;
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
	
	private QuestionService questionService;
	
	/**
	 * @param questionService the questionService to set
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
	@PostMapping("/question")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<SurveyQuestion> createQuestion(@RequestBody SurveyQuestionDto surveyQuestionDto) {
		SurveyQuestion surveyQuestion = questionService.createSurveyQuestion(surveyQuestionDto.toPojo());
		
		if(surveyQuestion == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return Mono.just(surveyQuestion);
	}

	/**
	 * Retrieve a list of {@link SurveyQuestion}.
	 * @return A promise for a list of {@link SurveyQuestion} objects.
	 */
	@GetMapping("/question")
	public Flux<SurveyQuestionDto> getQuestions(HttpServletResponse sr) {
		
		List<SurveyQuestion> questions = questionService.getAllSurveyQuestions();
		List<SurveyQuestionDto> questionsDto = new ArrayList<>();
		 

		for (SurveyQuestion sq : questions) {
			
			questionsDto.add(new SurveyQuestionDto(sq));
		} 
	
		sr.setStatus(200);
			
		// Fill Flux data with questionDto list content if it exists. 
		Flux<SurveyQuestionDto> data = Flux.fromIterable(questionsDto);

		return data;
	
	}
}
