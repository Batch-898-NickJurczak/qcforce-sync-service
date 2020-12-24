/**
 * 
 */
package com.revature.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.models.QuestionType;
import com.revature.models.SurveyQuestion;


/**
 * @author Work From Home
 *
 */
public class SurveyQuestionDto implements Dto<SurveyQuestion>{
	
	private int id;
	
	private LocalDateTime createdOn;
	
	private String type;
	
	private int version;
	
	private List<String> question;

	/**
	 * 
	 */
	public SurveyQuestionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param createdOn
	 * @param type
	 * @param version
	 * @param question
	 */
	public SurveyQuestionDto(int id, LocalDateTime createdOn, String type, int version, List<String> question) {
		super();
		this.id = id;
		this.createdOn = createdOn;
		this.type = type;
		this.version = version;
		this.question = question;
	}
	
	/**
	 * @param id
	 * @param createdOn
	 * @param type
	 * @param version
	 * @param question
	 */
	public SurveyQuestionDto(SurveyQuestion surveyQuestion) {
		super();
		this.id = surveyQuestion.getId();
		this.createdOn = surveyQuestion.getCreatedOn();
		this.type = surveyQuestion.getType().toString();
		this.version = surveyQuestion.getVersion();
		this.question = surveyQuestion.getQuestion();
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
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + id;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyQuestionDto other = (SurveyQuestionDto) obj;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (id != other.id)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SurveyQuestionDto [id=" + id + ", createdOn=" + createdOn + ", type=" + type + ", version=" + version
				+ ", question=" + question + "]";
	}

	@Override
	public SurveyQuestion toPojo() {
		return new SurveyQuestion(this.getId(), 
				this.getCreatedOn(), 
				QuestionType.valueOf(this.getType()), 
				this.getVersion(), 
				this.getQuestion());
	}
	
	

}
