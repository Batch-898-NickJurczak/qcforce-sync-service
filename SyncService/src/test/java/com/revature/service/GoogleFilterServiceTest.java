package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.models.FormResponse;

@ExtendWith(SpringExtension.class)
class GoogleFilterServiceTest {

	@Mock
	private DataRetrievalService dataRetrievalService;

	@InjectMocks
	private GoogleFilterImpl googleFilter;

	@Test
	public void testRawToString() {
		List<List<Object>> data = new ArrayList<List<Object>>();
		List<List<String>> data2 = new ArrayList<List<String>>();
		assertEquals(googleFilter.convertRawToStringList(data).getClass(), data2.getClass());
	}


	@Test
	void testFilterDup() {
		List<List<String>> fakeData = new ArrayList<List<String>>();
		List<String> fakeRowDataQuestions = new ArrayList<String>();
		List<String> fakeRowDataAnswers = new ArrayList<String>();

		String fakeQuestions = "Timestamp--Name (Optional)--Email (Optional)--What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)--How satisfied were you with the Training?--Any overall feedback (Training / Trainer / QC)--Any specific training issues you would like to mention?--Where is your training location?--What batch are you in?--What batch are you in?--What batch are you in?--What batch are you in?--What batch are you in?--How clear were the projects requirements/expectations?--How well has training prepared you to work on this project?--Project feedback:--What is your background coming into the program?--How much programming experience did you have before coming to training?--Please rate your level of understanding of last week's topics:--Batch Name--Please rate the pace of training for last week--Please give your ratings for the list given below : [Materials and content provided were helpful]--Please give your ratings for the list given below : [Training activities were well organized]--Please give your ratings for the list given below : [Questions were encouraged and answered]--Please give your ratings for the list given below : [Training and Projects met my expectations]--Did you go through the SPARK online program on RevaturePro before coming here?--Were your one-on-one and online assessment conducted last week of training?";
		String fakeAnswers = "3/10/2020 9:36:02--Not a real name--notareal@email.com--Week A--4--This is a comment.--USF--2003 Mar02 AP-USF Java--Non-STEM major--3--3--Agree--Disagree--Agree--Agree--No--Yes";
		String[] arrfakeQuestions = fakeQuestions.split("--");
		String[] arrfakeAnswers = fakeAnswers.split("--");

		for (String a : arrfakeQuestions) {
			fakeRowDataQuestions.add(a);
		}
		fakeData.add(fakeRowDataQuestions);
		for (String a : arrfakeAnswers) {
			fakeRowDataAnswers.add(a);
		}
		fakeData.add(fakeRowDataAnswers);

		String expectedQuestions = "Timestamp--Name (Optional)--Email (Optional)--What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)--How satisfied were you with the Training?--Any overall feedback (Training / Trainer / QC)--Any specific training issues you would like to mention?--Where is your training location?--What batch are you in?--How clear were the projects requirements/expectations?--How well has training prepared you to work on this project?--Project feedback:--What is your background coming into the program?--How much programming experience did you have before coming to training?--Please rate your level of understanding of last week's topics:--Batch Name--Please rate the pace of training for last week--Please give your ratings for the list given below : [Materials and content provided were helpful]--Please give your ratings for the list given below : [Training activities were well organized]--Please give your ratings for the list given below : [Questions were encouraged and answered]--Please give your ratings for the list given below : [Training and Projects met my expectations]--Did you go through the SPARK online program on RevaturePro before coming here?--Were your one-on-one and online assessment conducted last week of training?";
		String[] arrExpectedQuestions = expectedQuestions.split("--");
		List<String> fakeExpectedQuestions = new ArrayList<String>();
		for (String a : arrExpectedQuestions) {
			fakeExpectedQuestions.add(a);
		}

		List<List<String>> results = googleFilter.filterAndMergeDup(fakeData);
		assertTrue(results.get(0).equals(fakeExpectedQuestions));
		assertEquals(results.get(0).size(), results.get(1).size());
		assertEquals(googleFilter.filterAndMergeDup(new ArrayList<List<String>>()).size(), 0);
	}

	@Test
	void testMapFormResponse() {

		List<List<Object>> fakeData = new ArrayList<List<Object>>();
		List<Object> fakeRowDataQuestions = new ArrayList<Object>();
		List<Object> fakeRowDataAnswers = new ArrayList<Object>();

		String fakeQuestions = "Timestamp--Name (Optional)--Email (Optional)--What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)--How satisfied were you with the Training?--Any overall feedback (Training / Trainer / QC)--Any specific training issues you would like to mention?--Where is your training location?--What batch are you in?--What batch are you in?--What batch are you in?--What batch are you in?--What batch are you in?--How clear were the projects requirements/expectations?--How well has training prepared you to work on this project?--Project feedback:--What is your background coming into the program?--How much programming experience did you have before coming to training?--Please rate your level of understanding of last week's topics:--Batch Name--Please rate the pace of training for last week--Please give your ratings for the list given below : [Materials and content provided were helpful]--Please give your ratings for the list given below : [Training activities were well organized]--Please give your ratings for the list given below : [Questions were encouraged and answered]--Please give your ratings for the list given below : [Training and Projects met my expectations]--Did you go through the SPARK online program on RevaturePro before coming here?--Were your one-on-one and online assessment conducted last week of training?";
		String fakeAnswers = "3/10/2020 9:36:02--Not a real name--notareal@email.com--Week A--4--This is a comment.--USF--2003 Mar02 AP-USF Java--Non-STEM major----3--3--Agree--Disagree--Agree--Agree--No--Yes";
		String[] arrfakeQuestions = fakeQuestions.split("--");
		String[] arrfakeAnswers = fakeAnswers.split("--");

		for (String a : arrfakeQuestions) {
			fakeRowDataQuestions.add(a);
		}
		fakeData.add(fakeRowDataQuestions);
		for (String a : arrfakeAnswers) {
			fakeRowDataAnswers.add(a);
		}
		fakeData.add(fakeRowDataAnswers);

		String expectedQuestions = "Timestamp--Name (Optional)--Email (Optional)--What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)--How satisfied were you with the Training?--Any overall feedback (Training / Trainer / QC)--Any specific training issues you would like to mention?--Where is your training location?--What batch are you in?--How clear were the projects requirements/expectations?--How well has training prepared you to work on this project?--Project feedback:--What is your background coming into the program?--How much programming experience did you have before coming to training?--Please rate your level of understanding of last week's topics:--Batch Name--Please rate the pace of training for last week--Please give your ratings for the list given below : [Materials and content provided were helpful]--Please give your ratings for the list given below : [Training activities were well organized]--Please give your ratings for the list given below : [Questions were encouraged and answered]--Please give your ratings for the list given below : [Training and Projects met my expectations]--Did you go through the SPARK online program on RevaturePro before coming here?--Were your one-on-one and online assessment conducted last week of training?";
		String[] arrExpectedQuestions = expectedQuestions.split("--");
		List<Object> fakeExpectedQuestions = new ArrayList<Object>();
		for (String a : arrExpectedQuestions) {
			fakeExpectedQuestions.add(a);
		} 
		fakeExpectedQuestions.remove(0);
		when(dataRetrievalService.retrieveRawSheetData()).thenReturn(fakeData);


		List<FormResponse> results = googleFilter.mapFormResponses();

		assertTrue(results.get(0).getQuestions().equals(fakeExpectedQuestions));
		assertEquals(results.get(0).getQuestions().size(), results.get(0).getAnswers().size());
	}
	
	@Test
	void testMapFormResponseEmptyData() {
		List<List<Object>> fakeData = new ArrayList<List<Object>>();
		when(dataRetrievalService.retrieveRawSheetData()).thenReturn(fakeData);
		List<FormResponse> results = googleFilter.mapFormResponses();
		FormResponse FR = new FormResponse();
		FR.toString();
		assertNotNull(results);
	}
}
