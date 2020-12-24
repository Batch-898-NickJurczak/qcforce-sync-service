package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.domain.Form;


/**
 * Used to save form count to database.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Repository
public interface FormRepo extends JpaRepository<Form,Integer>{

}
