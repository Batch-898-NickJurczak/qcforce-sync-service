package com.revature.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This class represents the information for a batch.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","batchId","name","startDate","endDate","skill","location","type","goodGrade","passingGrade","employeeAssignments"})
public class Batch {


/**
 *	variable of type {@link Integer} that represents the internal batch id. 
 */
@JsonProperty("id")
private Integer id;
/**
 *	variable of type {@link String} that represents the batch id. 
 */
@JsonProperty("batchId")
private String batchId;
/**
 *	variable of type {@link String} that represents the batch name. 
 */
@JsonProperty("name")
private String name;
/**
 *	variable of type {@link String} that represents the batch start date. 
 */
@JsonProperty("startDate")
private String startDate;
/**
 *	variable of type {@link String} that represents the batch end date. 
 */
@JsonProperty("endDate")
private String endDate;
/**
 *	variable of type {@link String} that represents the specific skill focus for the batch. 
 */
@JsonProperty("skill")
private String skill;
/**
 *	variable of type {@link String} that represents the batch location. 
 */
@JsonProperty("location")
private String location;
/**
 *	variable of type {@link String} that represents the batch type. 
 */
@JsonProperty("type")
private String type;
/**
 *	variable of type {@link String} that represents the batch acceptable grade. 
 */
@JsonProperty("goodGrade")
private Integer goodGrade;
/**
 *	variable of type {@link String} that represents the batch passing grade. 
 */
@JsonProperty("passingGrade")
private Integer passingGrade;
/**
 *	array of type {@link List}<{@link EmployeeAssignment}> that represents all the assigned employees the batch has. 
 */
@JsonProperty("employeeAssignments")
private List<EmployeeAssignment> employeeAssignments = null;
/**
 *	additional properties the class may have. 
 */
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();


/**
 * Gets internal batch id.
 * @return internal batch id.
 */
@JsonProperty("id")
public Integer getId() {
return id;
}

/**
 * Sets internal batch id.
 * @param id new internal batch id.
 */
@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

/**
 * Gets batch id.
 * @return batch id.
 */
@JsonProperty("batchId")
public String getBatchId() {
return batchId;
}

/**
 * Sets batch id.
 * @param batchId new batch id.
 */
@JsonProperty("batchId")
public void setBatchId(String batchId) {
this.batchId = batchId;
}

/**
 * Gets batch name.
 * @return batch name.
 */
@JsonProperty("name")
public String getName() {
return name;
}

/**
 * Sets batch name.
 * @param name new batch name.
 */
@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

/**
 * Gets batch start data.
 * @return batch start date.
 */
@JsonProperty("startDate")
public String getStartDate() {
return startDate;
}

/**
 * Sets batch start date.
 * @param startDate new batch start date.
 */
@JsonProperty("startDate")
public void setStartDate(String startDate) {
this.startDate = startDate;
}

/**
 * Gets batch end date.
 * @return batch end date.
 */
@JsonProperty("endDate")
public String getEndDate() {
return endDate;
}

/**
 * Sets batch end date.
 * @param endDate new batch end date.
 */
@JsonProperty("endDate")
public void setEndDate(String endDate) {
this.endDate = endDate;
}

/**
 * Gets batch skill.
 * @return batch skill.
 */
@JsonProperty("skill")
public String getSkill() {
return skill;
}

/**
 * Sets batch skill.
 * @param skill new batch skill.
 */
@JsonProperty("skill")
public void setSkill(String skill) {
this.skill = skill;
}

/**
 * Gets batch location.
 * @return batch location.
 */
@JsonProperty("location")
public String getLocation() {
return location;
}

/**
 * Sets batch location.
 * @param location new batch location.
 */
@JsonProperty("location")
public void setLocation(String location) {
this.location = location;
}

/**
 * Gets batch type.
 * @return batch type.
 */
@JsonProperty("type")
public String getType() {
return type;
}

/**
 * Sets batch type.
 * @param type new batch type.
 */
@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

/**
 * Gets batch acceptable grade.
 * @return acceptable good grade.
 */
@JsonProperty("goodGrade")
public Integer getGoodGrade() {
return goodGrade;
}

/**
 * Sets batch acceptable grade.
 * @param goodGrade new good grade.
 */
@JsonProperty("goodGrade")
public void setGoodGrade(Integer goodGrade) {
this.goodGrade = goodGrade;
}

/**
 * Gets batch passing grade.
 * @return passing grade.
 */
@JsonProperty("passingGrade")
public Integer getPassingGrade() {
return passingGrade;
}

/**
 * Sets batch passing grade.
 * @param passingGrade new passing grade.
 */
@JsonProperty("passingGrade")
public void setPassingGrade(Integer passingGrade) {
this.passingGrade = passingGrade;
}

/**
 * Gets a list of employees assigned to the batch.
 * @return employee assignment list.
 */
@JsonProperty("employeeAssignments")
public List<EmployeeAssignment> getEmployeeAssignments() {
return employeeAssignments;
}

/**
 * Sets the employees assigned to the batch.
 * @param employeeAssignments new assignment list.
 */
@JsonProperty("employeeAssignments")
public void setEmployeeAssignments(List<EmployeeAssignment> employeeAssignments) {
this.employeeAssignments = employeeAssignments;
}

/**
 * Gets additional properties.
 * @return additional properties.
 */
@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

/**
 * Sets additional properties
 * @param name property name
 * @param value property value
 */
@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
	return "Batch [getId()=" + getId() + ", getBatchId()=" + getBatchId() + ", getName()=" + getName()
			+ ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate() + ", getSkill()=" + getSkill()
			+ ", getLocation()=" + getLocation() + ", getType()=" + getType() + ", getGoodGrade()=" + getGoodGrade()
			+ ", getPassingGrade()=" + getPassingGrade() + ", getEmployeeAssignments()=" + getEmployeeAssignments()
			+ ", getAdditionalProperties()=" + getAdditionalProperties() + "]";
}





}