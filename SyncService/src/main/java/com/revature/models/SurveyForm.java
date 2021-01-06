package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * An object representing a QC Force survey form, contains
 * {@link SurveyQuestion}s.
 * 
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@Entity
@Table(name = "survey_form")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyForm {

	/**
	 * The id of the Survey object.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * The title describing the Survey.
	 */
	@Column(name = "title")
	private String title;

	/**
	 * The first and last names of the author of this survey, separated by a space.
	 */
	@Column(name = "created_by")
	private String createdBy;

	/**
	 * {@link LocalDateTime} The date for when the Survey was created.
	 */
	@Column(name = "created_on")
	private LocalDateTime createdOn;

	/**
	 * The version number of the survey. Starting at 1 and incrementing by 1.
	 */
	@Column(name = "version")
	private int version;

	/**
	 * The {@link SurveyQuestion}s the survey contains. Ordered by the order which
	 * they will appear in the survey.
	 * <p>
	 * <b>Examples:</b>
	 * </p>
	 * <ul>
	 * <li>questions[0]: The first question to appear when taking the survey.</li>
	 * <li>questions[1]: The second question to appear when taking the survey.</li>
	 * </ul>
	 */
	@ManyToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@JoinTable(name = "survey_question_jt", joinColumns = { @JoinColumn(name = "survey_id") }, inverseJoinColumns = {
			@JoinColumn(name = "survey_question_id") })
	private List<SurveyQuestion> questions;

	/**
	 * No args constructor, defaults are empty.
	 */
	public SurveyForm() {
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
	 * @param questions The {@link SurveyQuestion}s the survey contains. Ordered by
	 *                  the order which they will appear in the survey.
	 */
	public SurveyForm(int id, String title, String createdBy, LocalDateTime createdOn, int version,
			List<SurveyQuestion> questions) {
		this.id = id;
		this.title = title;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.version = version;
		this.questions = questions;
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

	public List<SurveyQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SurveyQuestion> questions) {
		this.questions = questions;
	}
}