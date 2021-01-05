package com.revature.dto;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.FormResponse;

public class FormResponseDto implements Dto<FormResponse> {

	/**
	 * variable of type {@link Integer} that represents the id linked to a form response. 
	 */
	private int formResponseId;

	/**
	 * variable of type {@link String} that represents the time the response was submitted. 
	 */
	private String timestamp;

	/**
	 * variable of type {@link Integer} that represents the id of the survey submitted. 
	 */
	private int surveyId;

	/**
	 * variable of type {@link List}{@link String} that represents the questions of the response. 
	 */
	private List<String> questions;

	/**
	 * variable of type {@link List}{@link String} that represents the answers to the questions of the response. 
	 */
	private List<String> answers;
	
	/**
	 * initializes the questions and answers arrays.
	 */
	public FormResponseDto() {
		super();
		this.questions = new ArrayList<String>();
		this.answers = new ArrayList<String>();
	}

	/**
	 * @param formResponseId
	 * @param timestamp
	 * @param surveyId
	 * @param questions
	 * @param answers
	 */
	public FormResponseDto(int formResponseId, String timestamp, int surveyId, List<String> questions,
			List<String> answers) {
		super();
		this.formResponseId = formResponseId;
		this.timestamp = timestamp;
		this.surveyId = surveyId;
		this.questions = questions;
		this.answers = answers;
	}

	/**
	 * Gets the form id.
	 * @return form id.
	 */
	public int getFormResponseId() {
		return this.formResponseId;
	}

	/**
	 * Sets the form id.
	 * @param formId new form id.
	 */
	public void setFormResponseId(int formResponseId) {
		this.formResponseId = formResponseId;
	}

	/**
	 * Gets the time stamp for the form.
	 * @return form time stamp
	 */
	public String getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Sets the form time stamp.
	 * @param timestamp new time stamp.
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets an {@link List}{@link String} of questions from the form.
	 * @return {@link List}{@link String} questions.
	 */
	public List<String> getQuestions() {
		return this.questions;
	}

	/** Sets the form questions.
	 * @param questions new {@link List}{@link String} of questions.
	 */
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	/**
	 * Gets an list of answers to the questions from the form.
	 * @return list of answers.
	 */
	public List<String> getAnswers() {
		return this.answers;
	}

	/** Sets the form answers.
	 * @param answers new list of answers.
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	
	/**
	 * Gets the surveyId
	 * @return surveyId
	 */
	public int getSurveyId() {
		return this.surveyId;
	}

	
	/**
	 * Sets surveyId 
	 * @param formSource new form source
	 */
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	@Override
	public String toString() {
		return "FormResponse [getFormResponseId()=" + getFormResponseId() + ", getTimestamp()=" + getTimestamp() + ", getQuestions()="
				+ getQuestions() + ", getAnswers()=" + getAnswers() + ", getSurveyId()=" + getSurveyId() + "]";
	}
	
	
	/**
	 * Converts current FormResponseDto instance to FormResponse instance.
	 * @return FormResponse
	 */
	@Override
	public FormResponse toPojo() {
		FormResponse formResponse = new FormResponse();
		formResponse.setFormId(this.getFormResponseId());
		formResponse.setTimestamp(this.getTimestamp());
		formResponse.setSourceId(String.valueOf(this.getSurveyId()));
		formResponse.setQuestions(this.getQuestions());
		formResponse.setAnswers(this.getAnswers());
		return formResponse;
	}

}
