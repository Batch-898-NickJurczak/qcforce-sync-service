package com.revature.service;

import java.util.List;

import com.revature.domain.Form;


/**
 * Handles CRUD for Forms.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
public interface FormService {
	
	/**
	 * Gets all forms.
	 * Not Implemented.
	 * @return list of all forms.
	 */
	public List<Form> getAllForms();
		
	/**
	 * Gets a form by an id.
	 * Not implemented.
	 * @param id form id.
	 * @return a {@link Form}.
	 */
	public Form getFormById(int id);
		
	/**
	 * Creates a new form.
	 * Not implemented.
	 * @param form a {@link Form}.
	 */
	public void createForm(Form form);
		
	/**
	 * Updates a form.
	 * Used to keep track of for responses.
	 * @param form {@link Form}.
	 */
	public void updateForm(Form form);
		
	/**
	 * Deletes a form.
	 * Not implemented.
	 * @param form a {@link Form}.
	 */
	public void deleteForm(Form form);
		

}
