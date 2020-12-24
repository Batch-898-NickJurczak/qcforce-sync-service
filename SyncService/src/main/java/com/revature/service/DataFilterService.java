package com.revature.service;

import java.util.List;

import com.revature.models.FormResponse;

/**
 * Filters raw data pulled from a data source and maps it to a readable format.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
public interface DataFilterService {

	
	/**
	 * Takes in raw data and converts it to a matrix including duplicate values.
	 * @param data raw data
	 * @return a matrix of row data including duplicate columns.
	 */
	public List<List<String>> convertRawToStringList(List<List<Object>> data);
	
	/**
	 * Takes in a matrix of data that needs to be filtered, Merges duplicate columns into one
	 * @param data data including duplicates
	 * @return a matrix of row data with merged duplicate columns.
	 */
	public List<List<String>> filterAndMergeDup(List<List<String>> data);
	
	/**
	 * Maps filtered row data to a list of form responses.
	 * @return a list of form responses. 
	 */
	public List<FormResponse> mapFormResponses();
}
