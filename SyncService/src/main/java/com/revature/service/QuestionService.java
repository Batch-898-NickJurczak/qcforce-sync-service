/**
 * 
 */
package com.revature.service;

import com.revature.dto.SurveyQuestionDto;
import com.revature.models.SurveyQuestion;

/**
 * @author Work From Home
 *
 */
public interface QuestionService {
	
	public SurveyQuestion getSurveyQuestion(int id);
	
	public SurveyQuestion createSurveyQuestion(SurveyQuestionDto surveyQuestionDto);

}
