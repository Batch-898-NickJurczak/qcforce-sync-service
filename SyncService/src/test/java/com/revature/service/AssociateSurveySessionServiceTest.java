package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.AssociateSurveySession;
import com.revature.repo.AssociateSurveySessionRepo;

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

		associateSurveySession = new AssociateSurveySession(0, 1, 2, "2010", false);
	}

	@Test
	void createAssociateSurveySession_withoutError() {
		when(repo.save(associateSurveySession)).thenReturn(associateSurveySession);

		AssociateSurveySession returned = service.createAssociateSurveySession(associateSurveySession.getAssociateId(),
				associateSurveySession.getSurveyId(), associateSurveySession.getBatchId());

		verify(repo).save(returned);

		assertEquals(associateSurveySession, returned);
	}

	@Test
	void createAssociateSurveySession_existingObjectInDatabase() {
		when(repo.save(associateSurveySession)).thenReturn(associateSurveySession);

		AssociateSurveySession associateSurveySessionCopy = new AssociateSurveySession(0, 1, 2, "2010", false);

		AssociateSurveySession returnedFirst = service.createAssociateSurveySession(
				associateSurveySession.getAssociateId(), associateSurveySession.getSurveyId(),
				associateSurveySession.getBatchId());
		
		when(repo.findByAssociateIdAndSurveyIdAndBatchId(1, 2, "2010")).thenReturn(associateSurveySession);

		AssociateSurveySession returnedSecond = service.createAssociateSurveySession(
				associateSurveySessionCopy.getAssociateId(), associateSurveySessionCopy.getSurveyId(),
				associateSurveySessionCopy.getBatchId());

		verify(repo, times(1)).save(returnedFirst);
		verify(repo, times(2)).findByAssociateIdAndSurveyIdAndBatchId(1, 2, "2010");

		assertEquals(returnedFirst, returnedSecond);
	}
	
	@Test
	void readAssociateSurveySession_withoutError() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenReturn(associateSurveySession);
		
		AssociateSurveySession returned = service.readAssociateSurveySession(associateSurveySession.getAssociateSurveySessionId());
		
		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());
		
		assertEquals(associateSurveySession, returned);
	}
	
	@Test
	void readAssociateSurveySession_notFound() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenThrow(EntityNotFoundException.class);
		
		AssociateSurveySession returned = service.readAssociateSurveySession(associateSurveySession.getAssociateSurveySessionId());
		
		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());
		
		assertEquals(null, returned);
	}
	
	@Test
	void updateAssociateSurveySession_withoutError() {
		when(repo.save(associateSurveySession)).thenReturn(associateSurveySession);
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenReturn(associateSurveySession);
		
		AssociateSurveySession returned = service.updateAssociateSurveySession(associateSurveySession);
		
		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());
		verify(repo).save(associateSurveySession);
		
		assertEquals(returned, associateSurveySession);
	}
	
	@Test
	void upateAssociateSurveySession_notFound() {
		when(repo.getOne(associateSurveySession.getAssociateSurveySessionId())).thenThrow(EntityNotFoundException.class);
		
		AssociateSurveySession returned = service.updateAssociateSurveySession(associateSurveySession);
		
		verify(repo).getOne(associateSurveySession.getAssociateSurveySessionId());
		
		assertEquals(null, returned);
	}
}
