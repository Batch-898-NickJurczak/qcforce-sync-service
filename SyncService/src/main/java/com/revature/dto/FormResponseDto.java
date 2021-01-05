package com.revature.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.FormResponse;
import com.revature.models.SurveyQuestion;
import com.revature.service.SurveyService;

public class FormResponseDto implements Dto<FormResponse> {

	/**
	 * variable of type {@link Integer} that represents the id linked to a form
	 * response.
	 */
	private int formResponseId;

	/**
	 * variable of type {@link String} that represents the time the response was
	 * submitted.
	 */
	private String timestamp;

	/**
	 * variable of type {@link Integer} that represents the id of the survey
	 * submitted.
	 */
	private int surveyId;

	/**
	 * variable of type {@link List}{@link String} that represents the answers to
	 * the questions of the response.
	 */
	private List<String> answers;

	/**
	 * initializes the answers array.
	 */
	public FormResponseDto() {
		super();
		this.answers = new ArrayList<String>();
	}

	/**
	 * @param formResponseId
	 * @param timestamp
	 * @param surveyId
	 * @param answers
	 */
	public FormResponseDto(int formResponseId, String timestamp, int surveyId, List<String> answers) {
		super();
		this.formResponseId = formResponseId;
		this.timestamp = timestamp;
		this.surveyId = surveyId;
		this.answers = answers;
	}

	/**
	 * @return the formResponseId
	 */
	public int getFormResponseId() {
		return formResponseId;
	}

	/**
	 * @param formResponseId the formResponseId to set
	 */
	public void setFormResponseId(int formResponseId) {
		this.formResponseId = formResponseId;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the surveyId
	 */
	public int getSurveyId() {
		return surveyId;
	}

	/**
	 * @param surveyId the surveyId to set
	 */
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	/**
	 * @return the answers
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	/**
	 * Converts current FormResponseDto instance to {@link FormResponse} instance.
	 * The questions field is not initialized at this time.
	 * 
	 * @return FormResponse
	 */
	@Override
	public FormResponse toPojo() {

		FormResponse formResponse = new FormResponse();
		formResponse.setFormId(this.getSurveyId());
		formResponse.setTimestamp(this.getTimestamp());
		formResponse.setSourceId(String.valueOf(this.getSurveyId()));
		formResponse.setQuestions(new ArrayList<String>());
		formResponse.setAnswers(this.getAnswers());
		return formResponse;
	}

}
