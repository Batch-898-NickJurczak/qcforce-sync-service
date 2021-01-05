package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dto.FormResponseDto;
import com.revature.models.AssociateSurveySession;
import com.revature.models.FormResponse;
import com.revature.util.AssociateSurveySessionUpdateException;
import com.revature.util.InvalidJWTException;
import com.revature.util.InvalidSurveyIdException;

/**
 * 
 * These are tests for the {@link FormResponseService}.
 *
 */
class FormResponseServiceTest {

	@Autowired
	@InjectMocks
	private FormResponseServiceImpl service;

	@Mock
	private AuthServiceImpl authService;

	@Mock
	private AssociateSurveySessionService associateSurveySessionService;

	@Mock
	private RabbitMQImpl messageService;

	FormResponse formResponse;

	AssociateSurveySession associateSurveySession;

	AssociateSurveySession updatedAssociateSurveySession;

	String token;

	Map<String, Object> claims;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		formResponse = new FormResponseDto(0, "now", 1, new ArrayList<String>(), new ArrayList<String>()).toPojo();
		token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
				+ "eyJzdXJ2ZXlJZCI6IjEiLCJzdXJ2ZVN1YklkIjoiMiIsImJhdGNoSWQiOiIyMDEwIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYyMzkwMjJ9"
				+ ".2vu-3XIYjH6nhw8yu_KQ3Vz75lG-IedsB_qv7PGdlvM";

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
		when(authService.verifyJWT(token)).thenReturn(true);
		when(authService.getClaim()).thenReturn(claims);
		when(associateSurveySessionService.readAssociateSurveySession((int) claims.get("surveySubId")))
				.thenReturn(associateSurveySession);
		when(associateSurveySessionService.updateAssociateSurveySession(updatedAssociateSurveySession))
				.thenReturn(updatedAssociateSurveySession);

		FormResponse returned = service.createFormResponse(formResponse, token);

		verify(authService).verifyJWT(token);
		verify(authService).getClaim();
		verify(associateSurveySessionService).readAssociateSurveySession((int) claims.get("surveySubId"));
		verify(associateSurveySessionService).updateAssociateSurveySession(updatedAssociateSurveySession);
		verify(messageService, Mockito.times(1)).sendSingularFormResponse(returned);

		assertEquals(formResponse, returned);
	}

	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if an invalid JWT is given,
	 * that the method will throw an InvalidJWTException.
	 */
	@Test
	void createFormResponse_invalidJWT() {
		when(authService.verifyJWT(token)).thenReturn(false);
		
		assertThrows(InvalidJWTException.class, () -> service.createFormResponse(formResponse, token));

		verify(authService).verifyJWT(token);
		verify(messageService, never()).sendSingularFormResponse(Mockito.any());
	}

	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if a valid JWT with an invalid
	 * Id reference to an {@link AssociateSurveySession} is given, that the method
	 * will throw an EntityNotFoundException.
	 */
	@Test
	void createFormResponse_invalidAssociateSurveySessionId() {
		when(authService.verifyJWT(token)).thenReturn(true);
		when(authService.getClaim()).thenReturn(claims);
		when(associateSurveySessionService.readAssociateSurveySession((int) claims.get("surveySubId")))
				.thenThrow(EntityNotFoundException.class);
		
		assertThrows(EntityNotFoundException.class, () -> service.createFormResponse(formResponse, token));

		verify(authService).verifyJWT(token);
		verify(authService).getClaim();
		verify(associateSurveySessionService).readAssociateSurveySession((int) claims.get("surveySubId"));
		verify(messageService, never()).sendSingularFormResponse(Mockito.any());
	}

	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if the
	 * updateAssociateSurveySession method of the {@link AssociateSurveySessionImpl}
	 * fails, that the method will throw an AssociateSurveySessionUpdateException.
	 */
	@Test
	void createFormResponse_updateAssociateSurveySessionError() {
		when(authService.verifyJWT(token)).thenReturn(true);
		when(authService.getClaim()).thenReturn(claims);
		when(associateSurveySessionService.readAssociateSurveySession((int) claims.get("surveySubId")))
				.thenReturn(associateSurveySession);
		when(associateSurveySessionService.updateAssociateSurveySession(updatedAssociateSurveySession))
				.thenThrow(AssociateSurveySessionUpdateException.class);
		
		assertThrows(AssociateSurveySessionUpdateException.class,
				() -> service.createFormResponse(formResponse, token));

		verify(authService).verifyJWT(token);
		verify(authService).getClaim();
		verify(associateSurveySessionService).readAssociateSurveySession((int) claims.get("surveySubId"));
		verify(associateSurveySessionService).updateAssociateSurveySession(updatedAssociateSurveySession);
		verify(messageService, never()).sendSingularFormResponse(Mockito.any());
	}

	/**
	 * This tests the createFormResponse method of the
	 * {@link FormResponseServiceImpl}. Ensures that if the surveyId of the
	 * {@link FormResponse} is not equal to the surveyId of the
	 * {@link AssociateSurveySession} provided by the JWT, the method will throw an
	 * InvalidSurveyIdException.
	 */
	@Test
	void createFormResponse_mismatchedSurveyIds() {
		when(authService.verifyJWT(token)).thenReturn(true);
		when(authService.getClaim()).thenReturn(claims);
		when(associateSurveySessionService.readAssociateSurveySession((int) claims.get("surveySubId")))
				.thenReturn(associateSurveySession);

		formResponse.setFormId(associateSurveySession.getSurveyId() + 1);

		assertThrows(InvalidSurveyIdException.class, () -> service.createFormResponse(formResponse, token));

		verify(authService).verifyJWT(token);
		verify(authService).getClaim();
		verify(associateSurveySessionService).readAssociateSurveySession((int) claims.get("surveySubId"));
		verify(messageService, never()).sendSingularFormResponse(Mockito.any());
	}

}
