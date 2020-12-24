package com.revature.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Values;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Values.Get;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.revature.domain.Form;

@ExtendWith(SpringExtension.class)
class GoogleRetrievalImplTest {

	/**
	 * Instance of a sheet service to connect to Google Sheets.
	 */
	@Mock
	Sheets sheetsService;

	/**
	 * Instance of Form Service.
	 */
	@Mock
	FormService formService;

	@InjectMocks
	GoogleRetrievalImpl retrievalService;

	@Test
	void testGetRawData() throws IOException {
		when(sheetsService.spreadsheets()).thenReturn(mock(Spreadsheets.class));
		when(sheetsService.spreadsheets().values()).thenReturn(mock(Values.class));
		when(sheetsService.spreadsheets().values().get(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mock(Get.class));
		when(sheetsService.spreadsheets().values().get("", "A1:1").execute()).thenReturn(new ValueRange());
		Form f = new Form();
		f.setFormId(-1);
		Form f2 = new Form();
		f.setFormId(5);
		when(formService.getFormById(1)).thenReturn(f, f2);
		retrievalService.retrieveRawSheetData();
	}

	@Test
	void testGetRawDataResultsNull() throws IOException {
		when(sheetsService.spreadsheets()).thenReturn(mock(Spreadsheets.class));
		when(sheetsService.spreadsheets().values()).thenReturn(mock(Values.class));
		when(sheetsService.spreadsheets().values().get(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mock(Get.class));
		when(sheetsService.spreadsheets().values().get("", "A1:1").execute()).thenReturn(null);
		Form f = new Form();
		f.setFormId(-1);
		when(formService.getFormById(1)).thenReturn(f);
		retrievalService.retrieveRawSheetData();
	}

	@Test
	void testGetRawDataPass() throws IOException {
		when(sheetsService.spreadsheets()).thenReturn(mock(Spreadsheets.class));
		when(sheetsService.spreadsheets().values()).thenReturn(mock(Values.class));
		when(sheetsService.spreadsheets().values().get(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mock(Get.class));
		List<List<Object>> obj = new ArrayList<List<Object>>();
		obj.add(new ArrayList<Object>());
		ValueRange v = new ValueRange();
		v.setValues(obj);
		when(sheetsService.spreadsheets().values().get("", "A1:1").execute()).thenReturn(v);
		Form f = new Form();
		f.setFormId(-1);

		when(formService.getFormById(1)).thenReturn(f);
		retrievalService.retrieveRawSheetData();
	}
}
