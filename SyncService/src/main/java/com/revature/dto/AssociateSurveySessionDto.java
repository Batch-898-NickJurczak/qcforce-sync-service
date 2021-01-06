package com.revature.dto;

import com.revature.models.AssociateSurveySession;

public class AssociateSurveySessionDto implements Dto<AssociateSurveySession> {

	int surveyId;

	int associateId;

	String batchId;

	@Override
	public AssociateSurveySession toPojo() {
		AssociateSurveySession associateSurveySession = new AssociateSurveySession(1, this.surveyId, this.associateId,
				this.batchId, false);
		return associateSurveySession;
	}

	/**
	 * @param surveyId
	 * @param associateId
	 * @param batchId
	 */
	public AssociateSurveySessionDto(int surveyId, int associateId, String batchId) {
		super();
		this.surveyId = surveyId;
		this.associateId = associateId;
		this.batchId = batchId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateId;
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
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
		AssociateSurveySessionDto other = (AssociateSurveySessionDto) obj;
		if (associateId != other.associateId)
			return false;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (surveyId != other.surveyId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssociateSurveySessionDto [surveyId=" + surveyId + ", associateId=" + associateId + ", batchId="
				+ batchId + "]";
	}

}
