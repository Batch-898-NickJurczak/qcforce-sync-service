package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.domain.Batch;
import com.revature.domain.Employee;

/**
 * 
 * @author Hannah and Brett
 * 
 * This class represents a completed and submitted survey. It corresponds to a previously created survey (by an admin)
 * and contains a list of responses to questions on that survey. Each response in the response list contains the ID
 * of a question on the created Survey.
 */
public class SurveySubmission {

	private Survey survey;
	
	private Employee takenBy;
	
	private Batch batch;
	
	private LocalDateTime createdOn;
	
	private List<SurveyQuestionResponse> answers;

	public SurveySubmission(Survey survey, Employee takenBy, Batch batch, LocalDateTime createdOn,
			List<SurveyQuestionResponse> answers) {
		super();
		this.survey = survey;
		this.takenBy = takenBy;
		this.batch = batch;
		this.createdOn = createdOn;
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((survey == null) ? 0 : survey.hashCode());
		result = prime * result + ((takenBy == null) ? 0 : takenBy.hashCode());
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
		SurveySubmission other = (SurveySubmission) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (survey == null) {
			if (other.survey != null)
				return false;
		} else if (!survey.equals(other.survey))
			return false;
		if (takenBy == null) {
			if (other.takenBy != null)
				return false;
		} else if (!takenBy.equals(other.takenBy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SurveySubmission [survey=" + survey + ", takenBy=" + takenBy + ", batch=" + batch + ", createdOn="
				+ createdOn + ", answers=" + answers + "]";
	}
	
	
}
