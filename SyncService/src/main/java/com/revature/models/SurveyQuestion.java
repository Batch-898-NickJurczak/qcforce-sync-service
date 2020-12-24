/**
 * 
 */
package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Work From Home
 *
 */
@Entity
@Table(name = "survey_question")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property ="id")
public class SurveyQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Transient
	private QuestionType type;
	
	@Column(name = "version")
	private int version;
	
	@Transient
	private List<String> question;

	public SurveyQuestion() {
		super();
	}

	/**
	 * @param id
	 * @param createdOn
	 * @param type
	 * @param version
	 * @param question
	 */
	public SurveyQuestion(int id, LocalDateTime createdOn, QuestionType type, int version, List<String> question) {
		super();
		this.id = id;
		this.createdOn = createdOn;
		this.type = type;
		this.version = version;
		this.question = question;
	}

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
	 * @return the type
	 */
	public QuestionType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(QuestionType type) {
		this.type = type;
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
	 * @return the question
	 */
	public List<String> getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(List<String> question) {
		this.question = question;
	}	
	
	

}
