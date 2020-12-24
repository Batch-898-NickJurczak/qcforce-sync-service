/**
 * 
 */
package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.SurveyQuestion;

/**
 * @author Work From Home
 *
 */
@Repository
public interface QuestionRepo extends JpaRepository<SurveyQuestion, Integer>{

}
