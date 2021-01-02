package com.revature.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.models.QuestionType;
import com.revature.models.SurveyQuestion;


/**
 * A data transfer object correlating to the {@link SurveyQuestion} pojo.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
public class SurveyQuestionDto implements Dto<SurveyQuestion>{
	
	/**
	 * The id of the SurveyQuestion object.
	 */
	private int id;
	
	/**
	 * {@link LocalDateTime}
	 * The date for when the SurveyQuestion was created.
	 */
	private LocalDateTime createdOn;
	
	/**
	 * {@link String}
	 * The type of question this SurveyQuestion relates to, will only contain:
     * "MULTIPLE_CHOICE", "SHORT_ANSWER", or "PICK_FROM_RANGE"
	 */
	private String type;
	
	/**
	 * The version number of the question. Starting at 1 and incrementing by 1.
	 */
	private int version;
	
	/**
	 * {@link List} of type {@link String}
	 * The actual content of the question. Position [0] will always contain the overarching question.
     * The rest of the array list corresponds with additional details from the "MULTIPLE_CHOICE" and "PICK_FROM_RANGE" types.
     * <b>Examples:</b>
     * <ul><li><b>MULTIPLE_CHOICE: </b></li>
	 * <li>[1] = "Answer choice #1"</li>
	 * <li>[2] = "Answer choice #2", ect.</li></ul>
	 * <br>
     * <ul><li><b>PICK_FROM_RANGE: </b></li>
	 * <li>[1] = "First item in range"</li>
	 * <li>[2] = "Second item in range", ect.</li></ul>
	 */
	private List<String> question;

	/**
	 * No args constructor, defaults are empty.
	 */
	public SurveyQuestionDto() {
		super();
	}

	/**
	 * All args constructor.
	 * @param id The id of the SurveyQuestion object.
	 * @param createdOn The date for when the SurveyQuestion was created.
	 * @param type The type of question this SurveyQuestion relates to, will only contain: "MULTIPLE_CHOICE", "SHORT_ANSWER", or "PICK_FROM_RANGE".
	 * @param version The version number of the question. Starting at 1 and incrementing by 1.
	 * @param question The actual content of the question. Position [0] will always contain the overarching question. The rest of the array list corresponds with additional details from the "MULTIPLE_CHOICE" and "PICK_FROM_RANGE" types.
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
	 * SurveyQuestion arg constructor. Takes in a SurveyQuestion object.
	 * @param surveyQuestion The {@link SurveyQuestion} from which to originate this Dto object.
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

	/**
	 * Converts this Dto object into a {@link SurveyQuestion} object.
	 * @return {@link SurveyQuestion}
	 */
	@Override
	public SurveyQuestion toPojo() {
		return new SurveyQuestion(this.getId(), 
				this.getCreatedOn(), 
				QuestionType.valueOf(this.getType()), 
				this.getVersion(), 
				this.getQuestion());
	}
	
	

}