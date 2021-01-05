package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.AssociateSurveySession;
import com.revature.repo.AssociateSurveySessionRepo;
import com.revature.util.AssociateSurveySessionUpdateException;

/**
 * 
 * These are tests for the {@link AssociateSurveySessionImpl}.
 *
 */
@SpringBootTest
class AssociateSurveySessionServiceTest {

	@Autowired
	@InjectMocks
	private AssociateSurveySessionServiceImpl service;

	@Mock
	private AssociateSurveySessionRepo repo;

	AssociateSurveySession associateSurveySession;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		associateSurveySession = new AssociateSurveySession(1, 1, 2, "2010", false);
	}

	/**
	 * This tests the createAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if valid parameters are
	 * given, that the newly persisted {@link AssociateSurveySession} object will be
	 * returned.
	 */
	@Test
	void createAssociateSurveySession_withoutError() {
		when(repo.save(associateSurveySession)).thenReturn(associateSurveySession);

		AssociateSurveySession returned = service.createAssociateSurveySession(associateSurveySession.getAssociateId(),
				associateSurveySession.getSurveyId(), associateSurveySession.getBatchId());

		verify(repo).save(returned);

		assertEquals(associateSurveySession, returned);
	}

	/**
	 * This tests the createAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if valid parameters are
	 * given that match an already persisted {@link AssociateSurveySession} entity,
	 * that the already persisted {@link AssociateSurveySession} object will be
	 * returned and no new entity will be persisted.
	 */
	@Test
	void createAssociateSurveySession_existingObjectInDatabase() {
		when(repo.findByAssociateIdAndSurveyIdAndBatchId(1, 2, "2010")).thenReturn(associateSurveySession);

		AssociateSurveySession returned = service.createAssociateSurveySession(associateSurveySession.getAssociateId(),
				associateSurveySession.getSurveyId(), associateSurveySession.getBatchId());

		verify(repo, times(1)).findByAssociateIdAndSurveyIdAndBatchId(1, 2, "2010");
		verify(repo, never()).save(Mockito.any());

		assertEquals(returned, associateSurveySession);
	}

	/**
	 * This tests the createAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if valid parameters are
	 * given, but an error was encountered while saving the
	 * {@link AssociateSurveySession}, null is returned.
	 */
	@Test
	void createAssociateSurveySession_withErrorSaving() {
		when(repo.save(associateSurveySession)).thenReturn(null);

		AssociateSurveySession returned = service.createAssociateSurveySession(associateSurveySession.getAssociateId(),
				associateSurveySession.getSurveyId(), associateSurveySession.getBatchId());

		verify(repo).save(associateSurveySession);

		assertEquals(null, returned);
	}

	/**
	 * This tests the readAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if valid parameters are
	 * given, that the matching {@link AssociateSurveySession} object will be
	 * returned.
	 */
	@Test
	void readAssociateSurveySession_withoutError() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenReturn(associateSurveySession);

		AssociateSurveySession returned = service
				.readAssociateSurveySession(associateSurveySession.getAssociateSurveySessionId());

		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());

		assertEquals(associateSurveySession, returned);
	}

	/**
	 * This tests the readAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if parameters are given that
	 * do not match any persisted {@link AssociateSurveySession} object, null will
	 * be returned.
	 */
	@Test
	void readAssociateSurveySession_notFound() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId()))
				.thenThrow(EntityNotFoundException.class);

		AssociateSurveySession returned = service
				.readAssociateSurveySession(associateSurveySession.getAssociateSurveySessionId());

		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());

		assertEquals(null, returned);
	}

	/**
	 * This tests the updateAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if valid parameters are
	 * given, that the matching {@link AssociateSurveySession} object will be
	 * updated and the updated entity will be returned.
	 */
	@Test
	void updateAssociateSurveySession_withoutError() {
		when(repo.save(associateSurveySession)).thenReturn(associateSurveySession);
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenReturn(associateSurveySession);

		AssociateSurveySession returned = service.updateAssociateSurveySession(associateSurveySession);

		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());
		verify(repo).save(associateSurveySession);

		assertEquals(returned, associateSurveySession);
	}

	/**
	 * This tests the updateAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if parameters are given that
	 * do not match any persisted {@link AssociateSurveySession} object, null will
	 * be returned.
	 */
	@Test
	void updateAssociateSurveySession_notFound() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId()))
				.thenThrow(EntityNotFoundException.class);

		AssociateSurveySession returned = service.updateAssociateSurveySession(associateSurveySession);

		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());

		assertEquals(null, returned);
	}

	/**
	 * This tests the updateAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if other fields besides the
	 * taken field of the {@link AssociateSurveySession} object are updated, then an
	 * AssociateSurveySessionUpdateException is thrown.
	 */
	@Test
	void updateAssociateSurveySession_modifiedReadOnlyFields() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenReturn(associateSurveySession);

		AssociateSurveySession modifiedReadOnly = new AssociateSurveySession(
				associateSurveySession.getAssociateSurveySessionId(), 2, 3, "2011", true);

		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());
		verify(repo, never()).save(Mockito.any());

		assertThrows(AssociateSurveySessionUpdateException.class,
				() -> service.updateAssociateSurveySession(modifiedReadOnly));
	}

	/**
	 * This tests the updateAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if the taken field of an
	 * {@link AssociateSurveySession} object is set to false, then an
	 * AssociateSurveySessionUpdateException is thrown.
	 */
	@Test
	void updateAssociateSurveySession_takenSetAsFalse() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenReturn(associateSurveySession);

		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());
		verify(repo, never()).save(Mockito.any());

		assertThrows(AssociateSurveySessionUpdateException.class,
				() -> service.updateAssociateSurveySession(associateSurveySession));
	}

	/**
	 * This tests the updateAssociateSurveySession method of the
	 * {@link AssociateSurveySessionImpl}. Ensures that if the taken field of the
	 * persisted {@link AssociateSurveySession} object is already set to true, then
	 * an AssociateSurveySessionUpdateException is thrown.
	 */
	@Test
	void updateAssociateSurveySession_takenAlreadySetAsTrue() {
		AssociateSurveySession takenSetAsTrue = new AssociateSurveySession(
				associateSurveySession.getAssociateSurveySessionId(), associateSurveySession.getAssociateId(),
				associateSurveySession.getSurveyId(), associateSurveySession.getBatchId(), true);

		when(repo.getOne(takenSetAsTrue.getAssociateSurveySessionId())).thenReturn(takenSetAsTrue);

		verify(repo).getOne(takenSetAsTrue.getAssociateSurveySessionId());
		verify(repo, never()).save(Mockito.any());

		assertThrows(AssociateSurveySessionUpdateException.class,
				() -> service.updateAssociateSurveySession(takenSetAsTrue));
	}
}
