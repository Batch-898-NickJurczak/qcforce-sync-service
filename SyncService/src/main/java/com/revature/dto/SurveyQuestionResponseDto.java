package com.revature.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.models.QuestionType;
import com.revature.models.SurveyQuestionResponse;
import com.revature.repo.SurveyQuestionRepo;

/**
 * 
 * @author Hannah Novak, Brett Addicott, Alma Alva, Yarashlee Cruz
 *
 * A data transfer object correlating to the {@link SurveyQuestionResponse} pojo.
 * 
 */

/**
 * Survey Submission Dto constructor which will set our submitted fields and
 * create a new dto object.
 */

public class SurveyQuestionResponseDto implements Dto<SurveyQuestionResponse> {

	private SurveyQuestionRepo questionRepo;

	private int surveyQuestionId;

	private LocalDateTime createdOn;

	private String type;

	private int version;

	private List<String> answer;

	
	/**
	 * SurveyQuestionResponseDto constructor which will set our submitted fields and create a new dto object. 
	 */
	public SurveyQuestionResponseDto(int surveyQuestionId, LocalDateTime createdOn, String type, int version, List<String> answer) {
		super();
		this.surveyQuestionId = surveyQuestionId;
		this.createdOn = createdOn;
		this.type = type;
		this.answer = answer;
	}

	/**
	 * SurveyQuestionResponse to Pojo will convert our response object we receive
	 * from the controller into a pojo which will be saved in our database.
	 */

	@Override
	public SurveyQuestionResponse toPojo() {
		SurveyQuestionResponse questionResponse = new SurveyQuestionResponse();
		questionResponse.setSurveyQuestion(questionRepo.getSurveyQuestion(this.getSurveyQuestionId()));
		questionResponse.setCreatedOn(this.createdOn);

		QuestionType Qtype = QuestionType.MULTIPLE_CHOICE;
		if (this.type == "MULTIPLE_CHOICE") {

			Qtype = QuestionType.MULTIPLE_CHOICE;
		}
		if (this.type == "SHORT_ANSWER") {
			Qtype = QuestionType.SHORT_ANSWER;
		}
		if (this.type == "PICK_FROM_RANGE") {
			Qtype = QuestionType.PICK_FROM_RANGE;
		}
		questionResponse.setType(Qtype);
		questionResponse.setVersion(this.getVersion());

		return questionResponse;

	}

	public int getSurveyQuestionId() {
		return surveyQuestionId;
	}

	public void setSurveyQuestionId(int surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + surveyQuestionId;
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
		SurveyQuestionResponseDto other = (SurveyQuestionResponseDto) obj;
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
		if (surveyQuestionId != other.surveyQuestionId)
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

	/**
	 * This class converts our dto object into a string object
	 */

	@Override
	public String toString() {
		return "SurveyQuestionResponseDto [surveyQuestionId=" + surveyQuestionId + ", createdOn=" + createdOn
				+ ", type=" + type + ", version=" + version + ", answer=" + answer + "]";
	}

}
