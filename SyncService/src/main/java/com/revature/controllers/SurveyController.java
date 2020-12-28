package com.revature.controllers;

import javax.servlet.http.HttpServletResponse;

import com.revature.service.SurveyService;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	SurveyService surveyService;
    
	/**
	 * Deletes a {@link SurveyForm} that matches the given id.
	 * @param id The id of the {@link SurveyForm} object to delete.
	 */
	@DeleteMapping("/survey/{id}")
	public void deleteSurveyForm(@PathVariable("id") int id, HttpServletResponse response) {
		
		if (surveyService.deleteSurveyForm(id)) {
			response.setStatus(200);
		
		} else {
			response.setStatus(400);
		}
	}
}
