
import java.time.LocalDateTime;
import java.util.List;

import com.revature.domain.Batch;
import com.revature.domain.Employee;
import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestionResponse;
import com.revature.models.SurveySubmission;
import com.revature.repo.BatchRepo;
import com.revature.repo.EmployeeRepo;
import com.revature.repo.SurveyFormRepo;
import com.revature.repo.SurveyQuestionResponseRepo;

/**
 * 
 * @author Hannah and Brett This class represents the Data Transfer Object for
 *         the survey submission object
 * 
 *
 */
public class SurveySubmissionDto implements Dto<SurveySubmission> {

	SurveyQuestionResponseRepo questionResponseRepo;

	BatchRepo batchRepo;

	SurveyFormRepo surveyRepo;

	EmployeeRepo employeeRepo;

	private int surveySubmissionId;

	private int surveyId;

	private int takenBy;

	private int batchId;

	private LocalDateTime createdOn;

	private List<Integer> answers;

	private boolean taken;

	@Override
	public SurveySubmission toPojo() {
		SurveySubmission surveySubmission = new SurveySubmission();

		List<SurveyQuestionResponse> answers = questionResponseRepo.getResponsesBySubmission(this.surveySubmissionId);

		Batch batch = batchRepo.findByBatchId(this.batchId);

		SurveyForm survey = surveyRepo.getSurveyForm(this.surveyId);

		Employee takenBy = employeeRepo.findEmployeeById(this.takenBy);
		
		surveySubmission.setSurveySubmissionId(this.surveySubmissionId);
		surveySubmission.setTakenBy(takenBy);
		surveySubmission.setTaken(this.taken);
		surveySubmission.setCreatedOn(this.createdOn);
		surveySubmission.setSurvey(survey);
		surveySubmission.setAnswers(answers);
		surveySubmission.setBatch(batch);

		return surveySubmission;

	}

	public SurveySubmissionDto(int surveySubmissionId, int surveyId, int takenBy, int batchId, LocalDateTime createdOn,
			List<Integer> answers, boolean taken) {
		super();
		this.surveySubmissionId = surveySubmissionId;
		this.surveyId = surveyId;
		this.takenBy = takenBy;
		this.batchId = batchId;
		this.createdOn = createdOn;
		this.answers = answers;
		this.taken = taken;
	}

	@Override
	public String toString() {
		return "SurveySubmissionDto [surveySubmissionId=" + surveySubmissionId + ", surveyId=" + surveyId + ", takenBy="
				+ takenBy + ", batchId=" + batchId + ", createdOn=" + createdOn + ", answers=" + answers + ", taken="
				+ taken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + batchId;
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + surveyId;
		result = prime * result + surveySubmissionId;
		result = prime * result + (taken ? 1231 : 1237);
		result = prime * result + takenBy;
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
		SurveySubmissionDto other = (SurveySubmissionDto) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (batchId != other.batchId)
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (surveyId != other.surveyId)
			return false;
		if (surveySubmissionId != other.surveySubmissionId)
			return false;
		if (taken != other.taken)
			return false;
		if (takenBy != other.takenBy)
			return false;
		return true;
	}

	public int getSurveySubmissionId() {
		return surveySubmissionId;
	}

	public void setSurveySubmissionId(int surveySubmissionId) {
		this.surveySubmissionId = surveySubmissionId;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public int getTakenBy() {
		return takenBy;
	}

	public void setTakenBy(int takenBy) {
		this.takenBy = takenBy;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public List<Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Integer> answers) {
		this.answers = answers;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

}
