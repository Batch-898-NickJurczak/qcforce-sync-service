package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.SurveyForm;



/**
 * A JpaRepository implementation for interacting with {@link SurveyForm}
 * objects stored in the database.
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@Repository
public interface SurveyRepo extends JpaRepository<SurveyForm, Integer>{
    
}
