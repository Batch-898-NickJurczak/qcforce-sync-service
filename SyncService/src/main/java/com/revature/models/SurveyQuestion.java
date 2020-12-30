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
 * An object representing an individual question to be found in a QCForce {@link Survey}.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
@Entity
@Table(name = "survey_question")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property ="id")
public class SurveyQuestion {
	
	/**
	 * The id of this SurveyQuestion object.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	/**
	 * {@link LocalDateTime}
	 * The date for when this SurveyQuestion was created.
	 */
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	/**
	 * {@link QuestionType}
	 * The type of question this SurveyQuestion relates to.
	 */
	@Transient
	private QuestionType type;
	
	/**
	 * The version number of the question. Starting at 1 and incrementing by 1.
	 */
	@Column(name = "version")
	private int version;
	
	/**
	 * {@link List} of type {@link String}.
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
	@Transient
	private List<String> question;

	/**
	 * No args constructor, defaults are empty.
	 */
	public SurveyQuestion() {
		super();
	}

	/**
	 * All args constructor.
	 * @param id The id of this SurveyQuestion object.
	 * @param createdOn {@link LocalDateTime} The date for when this SurveyQuestion was created.
	 * @param type {@link QuestionType} The type of question this SurveyQuestion relates to. 
	 * @param version The version number of the question. Starting at 1 and incrementing by 1.
	 * @param question {@link List} of type {@link String}. The actual content of the question. 
	 * Position [0] will always contain the overarching question. The rest of the array list 
	 * corresponds with additional details from the "MULTIPLE_CHOICE" and "PICK_FROM_RANGE" types.
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
		SurveyQuestion other = (SurveyQuestion) obj;
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
		if (type != other.type)
			return false;
		if (version != other.version)
			return false;
		return true;
	}	
	
	

}
