package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.FormResponseDto;
import com.revature.models.FormResponse;

/**
 * 
 * This controller currently handles end points for the {@link FormResponse}
 *
 */
@RestController
@CrossOrigin
public class SurveyResponseController {
	
	/**
	 * This method handles the end point for creating a {@link FormResponse} object.
	 * 
	 * @param token
	 * @param formResponseDto
	 * @return ResponseEntity<Boolean>
	 */
	@PostMapping("/survey/response/{token}")
	public ResponseEntity<Boolean> createFormResponse(@PathVariable("token")String token, @RequestBody FormResponseDto formResponseDto ) {
		return null;
	}
	
}
