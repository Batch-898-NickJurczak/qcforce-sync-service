package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.models.SurveyForm;


/**
 * Used to save form count to database.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Repository
public interface SurveyRepo extends JpaRepository<SurveyForm,Integer>{

}
