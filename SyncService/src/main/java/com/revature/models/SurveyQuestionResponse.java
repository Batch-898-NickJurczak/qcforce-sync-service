package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Hannah and Brett
 * This class represents a single response to a survey question. Each instance of a response must be linked
 * to a survey question (has a surveyQuestion id)
 * 
 * 
 */
@Entity
@Table(name = "survey_question_responses")
public class SurveyQuestionResponse {
	
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name = "survey_question_id")
	private SurveyQuestion surveyQuestion;
    
	@ManyToOne
	@JoinColumn(name = "survey_submission_id")
	private SurveySubmission surveySubmission;
	
	@Column(name = "created_on")
    private LocalDateTime createdOn;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
    private QuestionType type;
    
	@Column
    private int version;
    
	@Column
    private List<String> answer;

    
	public SurveyQuestionResponse(SurveyQuestion surveyQuestion, SurveySubmission surveySubmission,
			LocalDateTime createdOn, QuestionType type, int version, List<String> answer) {
		super();
		this.surveyQuestion = surveyQuestion;
		this.surveySubmission = surveySubmission;
		this.createdOn = createdOn;
		this.type = type;
		this.version = version;
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((surveyQuestion == null) ? 0 : surveyQuestion.hashCode());
		result = prime * result + ((surveySubmission == null) ? 0 : surveySubmission.hashCode());
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
		SurveyQuestionResponse other = (SurveyQuestionResponse) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (surveyQuestion == null) {
			if (other.surveyQuestion != null)
				return false;
		} else if (!surveyQuestion.equals(other.surveyQuestion))
			return false;
		if (surveySubmission == null) {
			if (other.surveySubmission != null)
				return false;
		} else if (!surveySubmission.equals(other.surveySubmission))
			return false;
		if (type != other.type)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SurveyQuestionResponse [surveyQuestion=" + surveyQuestion + ", surveySubmission=" + surveySubmission
				+ ", createdOn=" + createdOn + ", type=" + type + ", version=" + version + ", answer=" + answer + "]";
	}

	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	public SurveySubmission getSurveySubmission() {
		return surveySubmission;
	}

	public void setSurveySubmission(SurveySubmission surveySubmission) {
		this.surveySubmission = surveySubmission;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}
    
    

}