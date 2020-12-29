package com.revature.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.models.Survey;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;

public class SurveyDto implements Dto<SurveyForm> {

	private int id;

	private int version;

	private String title;

	private int createdBy;

	private LocalDateTime createdOn;

	private List<SurveyQuestion> question;

	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}


	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * @return the createdOn
	 */
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}


	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}


	/**
	 * @return the question
	 */
	public List<SurveyQuestion> getQuestion() {
		return question;
	}


	/**
	 * @param question the question to set
	 */
	public void setQuestion(List<SurveyQuestion> question) {
		this.question = question;
	}


	/**
	 * @param id
	 * @param version
	 * @param title
	 * @param createdBy
	 * @param createdOn
	 * @param question
	 */
	public SurveyDto(SurveyForm survey) {
		super();
		this.id = survey.getId();
		this.version = survey.getVersion();
		this.title = survey.getTitle();
		this.createdBy = survey.getCreatedBy();
		this.createdOn = survey.getCreatedOn();
		this.question = survey.getQuestion();
	}


	@Override
	public SurveyForm toPojo() {
		SurveyForm survey = new SurveyForm();
		survey.setId(this.getId());
		survey.setVersion(this.getVersion());
		survey.setTitle(this.getTitle());
		survey.setCreatedBy(this.getCreatedBy());
		survey.setCreatedOn(this.getCreatedOn());
		survey.setQuestion(this.getQuestion());
		return survey;
	}

}
