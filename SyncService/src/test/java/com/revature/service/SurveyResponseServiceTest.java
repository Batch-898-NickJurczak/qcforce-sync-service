package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dto.FormResponseDto;
import com.revature.models.AssociateSurveySession;
import com.revature.models.FormResponse;
import com.revature.repo.FormResponseRepo;

class SurveyResponseServiceTest {

	@Autowired
	@InjectMocks
	private FormResponseServiceImpl service;

	@Mock
	private FormResponseRepo repo;

	@Mock
	private AuthServiceImpl authService;

	@Mock
	private AssociateSurveySessionServiceImpl associateSurveySessionService;

	FormResponse formResponse;

	AssociateSurveySession associateSurveySession;

	AssociateSurveySession updatedAssociateSurveySession;

	String token;
	Map<String, Object> claims;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		formResponse = new FormResponseDto(0, "now", 1, new ArrayList<String>(), new ArrayList<String>()).toPojo();
		token = "asl;dkja;owi";
		claims = new HashMap<String, Object>();
		claims.put("surveySubId", 1);

		associateSurveySession = new AssociateSurveySession(0, 1, 2, "2010", false);
		updatedAssociateSurveySession = new AssociateSurveySession(0, 1, 2, "2010", true);
	}

	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if valid parameters are given,
	 * that the newly persisted {@link FormResponse} object will be returned.
	 */
	@Test
	void createFormResponse_withoutError() {
		when(repo.save(formResponse)).thenReturn(formResponse);
		when(authService.verifyJWT(token)).thenReturn(true);
		when(authService.getClaim()).thenReturn(claims);
		when(associateSurveySessionService.readAssociateSurveySession((int) claims.get("surveySubId")))
				.thenReturn(associateSurveySession);
		when(associateSurveySessionService.updateAssociateSurveySession(updatedAssociateSurveySession))
				.thenReturn(updatedAssociateSurveySession);

		FormResponse returned = service.createFormResponse(formResponse, token);

		verify(repo).save(returned);
		verify(authService).verifyJWT(token);
		verify(authService.getClaim());
		verify(associateSurveySessionService).readAssociateSurveySession((int) claims.get("surveySubId"));
		verify(associateSurveySessionService).updateAssociateSurveySession(updatedAssociateSurveySession);

		assertEquals(formResponse, returned);
	}

	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if an invalid JWT is given,
	 * that the method will return null, and that it will not persist the {@link FormResponse}.
	 */
	@Test
	void createFormResponse_invalidJWT() {
		when(authService.verifyJWT(token)).thenReturn(false);

		FormResponse returned = service.createFormResponse(formResponse, token);

		verify(repo, never()).save(returned);
		verify(authService).verifyJWT(token);
		assertEquals(returned, null);

	}
	
	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if a valid JWT with an invalid Id reference to an {@link AssociateSurveySession} is given,
	 * that the method will return null, and that it will not persist the {@link FormResponse}.
	 */
	@Test
	void createFormResponse_invalidAssociateSurveySessionId() {
		when(repo.save(formResponse)).thenReturn(formResponse);
		when(authService.verifyJWT(token)).thenReturn(true);
		when(authService.getClaim()).thenReturn(claims);
		when(associateSurveySessionService.readAssociateSurveySession((int) claims.get("surveySubId")))
				.thenReturn(null);

		FormResponse returned = service.createFormResponse(formResponse, token);

		verify(repo, never()).save(returned);
		verify(authService).verifyJWT(token);
		verify(authService.getClaim());
		verify(associateSurveySessionService).readAssociateSurveySession((int) claims.get("surveySubId"));

		assertEquals(null, returned);
	}
	
	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if the updateAssociateSurveySession method of the {@link AssociateSurveySessionImpl} fails,
	 * that the method will return null, and that it will not persist the {@link FormResponse}.
	 */
	@Test
	void createFormResponse_updateAssociateSurveySessionError() {
		when(repo.save(formResponse)).thenReturn(formResponse);
		when(authService.verifyJWT(token)).thenReturn(true);
		when(authService.getClaim()).thenReturn(claims);
		when(associateSurveySessionService.readAssociateSurveySession((int) claims.get("surveySubId")))
				.thenReturn(associateSurveySession);
		when(associateSurveySessionService.updateAssociateSurveySession(updatedAssociateSurveySession))
				.thenReturn(null);

		FormResponse returned = service.createFormResponse(formResponse, token);

		verify(repo, never()).save(returned);
		verify(authService).verifyJWT(token);
		verify(authService.getClaim());
		verify(associateSurveySessionService).readAssociateSurveySession((int) claims.get("surveySubId"));
		verify(associateSurveySessionService).updateAssociateSurveySession(updatedAssociateSurveySession);

		assertEquals(null, returned);
	}

}
