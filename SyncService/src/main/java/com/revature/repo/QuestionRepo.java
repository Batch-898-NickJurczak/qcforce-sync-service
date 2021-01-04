/**
 * 
 */
package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.SurveyQuestion;

/**
 * A JpaRepository implementation for interacting with {@link SurveyQuestion} objects stored in the database.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
@Repository
public interface QuestionRepo extends JpaRepository<SurveyQuestion, Integer>{

}
