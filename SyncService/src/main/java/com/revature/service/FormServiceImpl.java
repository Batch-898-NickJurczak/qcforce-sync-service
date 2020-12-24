package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.domain.Form;
import com.revature.repo.FormRepo;

/**
 * Used to keep a count of the form responses sent through the messaging queue.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Service
public class FormServiceImpl implements FormService {

	/**
	 * Instance of a FormRepo
	 */
	private FormRepo formRepo;
	
	@Autowired
	public void setFormRepo(FormRepo formRepo)
	{
		this.formRepo = formRepo;
	}
	
	@Override
	public List<Form> getAllForms() {
		return formRepo.findAll();
	}

	@Override
	public Form getFormById(int id) {
		return formRepo.findById(id).get();
	}

	@Override
	public void createForm(Form form) {
		formRepo.save(form);
	}

	@Override
	public void updateForm(Form form) {
		formRepo.findById(form.getId()).ifPresent((existingForm)->formRepo.save(form));
	}

	@Override
	public void deleteForm(Form form) {
		formRepo.delete(form);
	}

}
