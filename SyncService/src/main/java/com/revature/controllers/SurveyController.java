package com.revature.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller that manages all SurveyForm related endpoints.
 * 
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@RestController
@CrossOrigin
public class SurveyController {
    
	/**
	 * Deletes a {@link SurveyForm} that matches the given id.
	 * @param id The id of the {@link SurveyForm} object to delete.
	 */
	@DeleteMapping("/survey/{id}")
	public void deleteSurveyForm(@PathVariable("id") int id) {
		// TODO: make this
	}
}
