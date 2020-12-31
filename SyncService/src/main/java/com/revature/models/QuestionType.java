package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "question_type")
public enum QuestionType {

	@Column
	MULTIPLE_CHOICE, 
	
	@Column
	SHORT_ANSWER,
	
	@Column
	PICK_FROM_RANGE

}
