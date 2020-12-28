package com.revature.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;
import com.revature.repo.QuestionRepo;
import com.revature.service.QuestionService;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

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
	 * Retrieve a {@link SurveyQuestion} that matches the given id.
	 * 
	 * @return A promise for a {@link SurveyQuestion} object.
	 */
	@GetMapping("/question/{id}")
	public Flux<SurveyQuestionDto> getQuestion(@PathVariable("id") int id, HttpServletResponse response) {

		SurveyQuestion question = questionService.getSurveyQuestion(id);

		SurveyQuestionDto questionDto = new SurveyQuestionDto(question);
		
		response.setStatus(200);

		return Flux.just(questionDto);
	}
}
