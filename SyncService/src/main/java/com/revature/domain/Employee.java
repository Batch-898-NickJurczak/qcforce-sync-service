package com.revature.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This class represents the information for an Employee.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"email","firstName","lastName"})
public class Employee {

	/**
	 *	variable of type {@link String} that represents the employee's email. 
	 */
	@JsonProperty("email")
	private String email;
	/**
	 *	variable of type {@link String} that represents the employee's first name. 
	 */
	@JsonProperty("firstName")
	private String firstName;
	/**
	 *	variable of type {@link String} that represents the employee's last name. 
	 */
	@JsonProperty("lastName")
	private String lastName;
	/**
	 *	additional properties an employee might have.
	 */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * Gets the Employee's email.
	 * @return email. 
	 */
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	/**Sets the Employee's email.
	 * @param email new email.
	 */
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the Employee's first name.
	 * @return first name.
	 */
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the Employee's first name.
	 * @param firstName new first name.
	 */
	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the Employee's last name.
	 * @return last name.
	 */
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the Employee's last name.
	 * @param lastName new last name
	 */
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets additional properties for an Employee.
	 * @return a map of additional properties.
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * Sets additional properties for an Employee.
	 * @param name property name.
	 * @param value property value.
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Employee [getEmail()=" + getEmail() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getAdditionalProperties()=" + getAdditionalProperties() + "]";
	}



}