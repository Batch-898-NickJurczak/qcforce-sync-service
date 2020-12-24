package com.revature.service;

import java.util.List;

/**
 * Used to retrieve data from a data source.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
public interface DataRetrievalService {
	
	/**
	 * Retrieves raw data form a data source.
	 * @return a matrix of raw data.
	 */
	public List<List<Object>> retrieveRawSheetData();
}
