package com.revature.service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;
import com.revature.repo.QuestionRepo;

/**
 * A standard implementation of the {@link QuestionService} interface for 
 * handling all the business code related to {@link SurveyQuestion} objects.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
@Service
public class QuestionServiceImpl implements QuestionService {
	
	/**
	 * The repository object this service needs to interact with {@link SurveyQuestion}s stored in the database.
	 */
	private QuestionRepo questionRepo;

	/**
	 * Set the {@link QuestionRepo} contained within this object. 
	 * Normally the Spring framework will set this through Autowiring. 
	 */
	@Autowired
	public void setQuestionRepo(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}

	/**
	 * Retrieves a {@link SurveyQuestion} object that relates to given id.
	 * @param id The id of the SurveyQuestion object.
	 * @return {@link SurveyQuestion}
	 */
	@Override
	public SurveyQuestion getSurveyQuestion(int id) {
		
		return questionRepo.getOne(id);
	}
	
	/**
	 * Creates a {@link SurveyQuestion} object given a {@link SurveyQuestionDto} input from the user.
	 * @param surveyQuestionDto The {@link SurveyQuestionDto} object that the user will input.
	 * @return {@link SurveyQuestion}
	 */
	@Override
	public SurveyQuestion createSurveyQuestion(SurveyQuestion surveyQuestion) {
		SurveyQuestion returned = null;
			
		try {
			
			returned = questionRepo.save(surveyQuestion);
			
		}catch(EntityNotFoundException e) {
			
			return null;
			
		}
		
		return returned;
	}
	
	 /** Retrieves a list of {@link SurveyQuestion} objects.
	 * @return A list of {@link SurveyQuestion}.
	 */
	@Override
	public List<SurveyQuestion> getAllSurveyQuestions() {
		return questionRepo.findAll();
	}
}