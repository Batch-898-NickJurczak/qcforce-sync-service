package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
