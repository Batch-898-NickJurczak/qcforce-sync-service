package com.revature.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.SurveyForm;
import com.revature.models.SurveyQuestion;

public class SurveyFormDto implements Dto<SurveyForm> {

    /**
     * The id of the Survey object.
     */
    private int id;

    /**
     * The title describing the Survey.
     */
    private String title;

    /**
     * The first and last names of the author of this survey, separated by a space.
     */
    private String createdBy;

    /**
     * {@link LocalDateTime} The date for when the Survey was created.
     */
    private LocalDateTime createdOn;

    /**
     * The version number of the survey. Starting at 1 and incrementing by 1.
     */
    private int version;

    /**
     * The {@link SurveyQuestion}s the survey contains. Ordered by the order which they will
     * appear in the survey.
     * <p><b>Examples:</b></p>
     * <ul><li>questions[0]: The first question to appear when taking the survey.</li>
     * <li>questions[1]: The second question to appear when taking the survey.</li></ul>
     */
    private List<SurveyQuestionDto> questions;

    /**
     * No args constructor, defaults are empty.
     */
    public SurveyFormDto() {
        super();
    }

    /**
     * All args constructor.
     * 
     * @param id        The id of the Survey object.
     * @param title     The title describing the Survey.
     * @param createdBy The first and last names of the author of this survey,
     *                  separated by a space.
     * @param createdOn {@link LocalDateTime} The date for when the Survey was
     *                  created.
     * @param version   The version number of the survey. Starting at 1 and
     *                  incrementing by 1.
     * @param questions The {@link SurveyQuestion}s the survey contains. Ordered by the order
     *                  which they will appear in the survey.
     */
    public SurveyFormDto(int id, String title, String createdBy, LocalDateTime createdOn, int version,
            List<SurveyQuestionDto> questions) {
        this.id = id;
        this.title = title;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.version = version;
        this.questions = questions;
    }

    /**
	 * SurveyForm arg constructor. Takes in a SurveyForm object.
	 * @param surveyForm The {@link SurveyForm} from which to originate this Dto object.
	 */
    public SurveyFormDto(SurveyForm survey){
        this.id = survey.getId();
        this.title = survey.getTitle();
        this.createdBy = survey.getCreatedBy();
        this.createdOn = survey.getCreatedOn();
        this.version = survey.getVersion();

        List<SurveyQuestionDto> questions = new ArrayList<>(survey.getQuestions().size());

        survey.getQuestions().forEach(question -> {
            questions.add(new SurveyQuestionDto(question));
        });

        this.questions = questions;
    }

    /**
	 * Converts this Dto object into a {@link SurveyForm} object.
	 * @return {@link SurveyForm}
	 */
    @Override
    public SurveyForm toPojo() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setId(this.getId());
        surveyForm.setTitle(this.getTitle());
        surveyForm.setCreatedBy(this.getCreatedBy());
        surveyForm.setCreatedOn(this.getCreatedOn());
        surveyForm.setVersion(this.getVersion());

        List<SurveyQuestion> questions = new ArrayList<>(this.getQuestions().size());

        this.getQuestions().forEach(question -> {
            questions.add(question.toPojo());
        });

        surveyForm.setQuestions(questions);
        return surveyForm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<SurveyQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestionDto> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "SurveyFormDto [createdBy=" + createdBy + ", createdOn=" + createdOn + ", id=" + id + ", questions="
                + questions + ", title=" + title + ", version=" + version + "]";
    }

}
