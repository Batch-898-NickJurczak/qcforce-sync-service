package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *Standard template of a QC survey form that will be sent through a message queue.
 *@author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
public class FormResponse implements Serializable {

	/**
	 * Serializable version UID
	 */
	private static final long serialVersionUID = 9136762341724971453L;

	/**
	 * variable of type {@link Integer} that represents the id linked to a form response. 
	 */
	private int formId;

	/**
	 * variable of type {@link String} that represents the time the response was submited. 
	 */
	private String timestamp;

	/**
	 * variable of type {@link String} that represents the time the response was submited. 
	 */
	private String sourceId;

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
	public FormResponse() {
		super();
		this.questions = new ArrayList<String>();
		this.answers = new ArrayList<String>();
	}

	/**
	 * Gets the form id.
	 * @return form id.
	 */
	public int getFormId() {
		return formId;
	}

	/**
	 * Sets the form id.
	 * @param formId new form id.
	 */
	public void setFormId(int formId) {
		this.formId = formId;
	}

	/**
	 * Gets the time stamp for the form.
	 * @return form time stamp
	 */
	public String getTimestamp() {
		return timestamp;
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
		return questions;
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
		return answers;
	}

	/** Sets the form answers.
	 * @param answers new list of answers.
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	
	/**
	 * Gets the form source
	 * @return form source
	 */
	public String getSourceId() {
		return sourceId;
	}

	
	/**
	 * Sets form Source
	 * @param formSource new form source
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return "FormResponse [getFormId()=" + getFormId() + ", getTimestamp()=" + getTimestamp() + ", getQuestions()="
				+ getQuestions() + ", getAnswers()=" + getAnswers() + ", getFormSource()=" + getSourceId() + "]";
	}

	


}
