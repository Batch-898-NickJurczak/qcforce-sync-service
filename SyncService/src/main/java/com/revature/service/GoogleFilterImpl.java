package com.revature.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.config.SheetsServiceConfig;
import com.revature.models.FormResponse;

/**
 * Filters raw data retrieved from a Google Sheets spreadsheet.
 * 
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Service
public class GoogleFilterImpl implements DataFilterService {

	/**
	 * Instance of DataRetrievalService
	 */
	private DataRetrievalService dataRetrievalService;

	/**
	 * Instance of SheetsServiceConfig
	 */
	private SheetsServiceConfig sheetsServiceConfig;


	/**
	 * @param dataRetrievalService DataRetrievalService bean
	 * @param sheetsServiceConfig SheetsServiceConfig bean
	 */
	public GoogleFilterImpl(DataRetrievalService dataRetrievalService, SheetsServiceConfig sheetsServiceConfig) {
		this.dataRetrievalService = dataRetrievalService;
		this.sheetsServiceConfig = sheetsServiceConfig;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<List<String>> convertRawToStringList(List<List<Object>> data) {
		// Create a matrix of strings that will be populated with the converted raw data
		// objects
		List<List<String>> listOfLists = new ArrayList<List<String>>();

		// Inputs the raw data objects of every row into the string matrix.
		// Objects are casted from type Object to type String.
		for (@SuppressWarnings("rawtypes")
		List row : data) {
			listOfLists.add(row);
		}
		return listOfLists;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<List<String>> filterAndMergeDup(List<List<String>> data) {

		if (data.isEmpty()) {
			return new ArrayList<List<String>>();
		}

		// The first row in the data matrix is a list of questions
		List<String> questions = data.get(0);

		// Instantiate a list of integers that indicate the duplicate
		// questions to remove or the duplicate columns to join.
		List<Integer> itemsToRemove = new ArrayList<>();

		/*
		 * Get index of all duplicate columns to be joined.
		 */
		for (int i = 1; i < questions.size() - 1; i++) {
			if (questions.get(i).equals(questions.get(i - 1))
					|| questions.get(i).equals(questions.get(i + 1))) {

				itemsToRemove.add(i);
			}
		}

		/*
		 * Remove duplicate questions
		 */
		Set<String> set = new LinkedHashSet<>();
		set.addAll(questions);
		questions.clear();
		questions.addAll(set);
		data.set(0, questions);

		/*
		 * Joins duplicate Columns
		 */
		for (int i = 1; i < data.size(); i++) {

			for (int j = itemsToRemove.size() - 1; j >= 0; j--) {

				if (data.get(i).get(itemsToRemove.get(j).intValue()).toString().isEmpty()) {

					data.get(i).remove(itemsToRemove.get(j).intValue());
				}
			}
		}

		/*
		 * Fill in missing ending columns
		 */

		int maxColumn = data.get(0).size();

		for (@SuppressWarnings("rawtypes")
		List row : data) {

			while (row.size() < maxColumn) {
				row.add("");
			}
		}

		return data;
	}

	@Override
	public List<FormResponse> mapFormResponses() {

		List<FormResponse> forms = new ArrayList<>();

		List<List<String>> filteredData = filterAndMergeDup(convertRawToStringList(dataRetrievalService.retrieveRawSheetData()));
		if (filteredData.isEmpty()) {
			return new ArrayList<>();
		}
		List<String> questions = filteredData.get(0);
		questions.remove(0);

		// Cycling through filtered data, create a new form response & add it to the
		// returned array
		for (int i = 1; i < filteredData.size(); i++) {
			FormResponse temp = new FormResponse();
			temp.setFormId(GoogleRetrievalImpl.currentRow + i - 1);
			temp.setSourceId(sheetsServiceConfig.getSPREAD_SHEET_ID());
			temp.setTimestamp(filteredData.get(i).get(0));
			List<String> answers = filteredData.get(i);
			answers.remove(0);
			temp.setQuestions(questions);
			temp.setAnswers(answers);
			forms.add(temp);
		}
		return forms;
	}

}
