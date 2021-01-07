package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is a model used to keep track of whether an associate has already
 * completed a given survey. It serves as a link between the associate and the
 * survey.
 */
@Entity
@Table(name = "associate_survey_session")
public class AssociateSurveySession {

	/**
	 * Auto-generated Integer for the id of this object
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "associate_survey_session_id")
	private int associateSurveySessionId;

	/**
	 * Integer for the id of the associate
	 */
	@Column(name = "associate_id")
	private int associateId;

	/**
	 * Integer for the id of the survey
	 */
	@Column(name = "survey_id")
	private int surveyId;

	/**
	 * String for the id of the batch
	 */
	@Column(name = "batch_id")
	private String batchId;

	/**
	 * Boolean representing whether the associate with id associateId has taken the
	 * survey with id surveyId
	 */
	@Column(name = "is_taken")
	private boolean isTaken;

	/**
	 * @param associateSurveySessionId
	 * @param associateId
	 * @param surveyId
	 * @param batchId
	 * @param isTaken
	 */
	public AssociateSurveySession(int associateSurveySessionId, int associateId, int surveyId, String batchId,
			boolean isTaken) {
		super();
		this.associateSurveySessionId = associateSurveySessionId;
		this.associateId = associateId;
		this.surveyId = surveyId;
		this.batchId = batchId;
		this.isTaken = isTaken;
	}
	

	/**
	 * @return the associateSurveySessionId
	 */
	public int getAssociateSurveySessionId() {
		return associateSurveySessionId;
	}

	/**
	 * @param associateSurveySessionId the associateSurveySessionId to set
	 */
	public void setAssociateSurveySessionId(int associateSurveySessionId) {
		this.associateSurveySessionId = associateSurveySessionId;
	}

	/**
	 * @return the associateId
	 */
	public int getAssociateId() {
		return associateId;
	}

	/**
	 * @param associateId the associateId to set
	 */
	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}

	/**
	 * @return the surveyId
	 */
	public int getSurveyId() {
		return surveyId;
	}

	/**
	 * @param surveyId the surveyId to set
	 */
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the isTaken
	 */
	public boolean isTaken() {
		return isTaken;
	}

	/**
	 * @param isTaken the isTaken to set
	 */
	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	@Override
	public String toString() {
		return "AssociateSurveySession [associateSurveySessionId=" + associateSurveySessionId + ", associateId="
				+ associateId + ", surveyId=" + surveyId + ", batchId=" + batchId + ", isTaken=" + isTaken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateId;
		result = prime * result + associateSurveySessionId;
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + (isTaken ? 1231 : 1237);
		result = prime * result + surveyId;
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
		AssociateSurveySession other = (AssociateSurveySession) obj;
		if (associateId != other.associateId)
			return false;
		if (associateSurveySessionId != other.associateSurveySessionId)
			return false;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (isTaken != other.isTaken)
			return false;
		if (surveyId != other.surveyId)
			return false;
		return true;
	}

}
