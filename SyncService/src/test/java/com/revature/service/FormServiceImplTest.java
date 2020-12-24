package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.domain.Form;
import com.revature.repo.FormRepo;

@ExtendWith(SpringExtension.class)
class FormServiceImplTest {

	@Mock
	FormRepo formRepo;

	@InjectMocks
	FormServiceImpl formService;

	@Test
	void testFindAll() {
		when(formRepo.findAll()).thenReturn(new ArrayList<Form>());
		assertNotNull(formService.getAllForms());
	}

	@Test
	void testGetFormByID() {
		when(formRepo.findById(1)).thenReturn(Optional.of(new Form()));
		assertNotNull(formService.getFormById(1));
	}

	@Test
	void testCreateForm() {
		formService.createForm(new Form());
		verify(formRepo, times(1)).save(new Form());
	}

	@Test
	void testUpdateForm() {
		when(formRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(new Form()));
		assertNotNull(formService.getFormById(Mockito.anyInt()));
		formService.updateForm(new Form());
	}

	@Test
	void testDeleteFormByID() {
		formService.deleteForm(new Form());
		verify(formRepo, times(1)).delete(new Form());
	}
}
