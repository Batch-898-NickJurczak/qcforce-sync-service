package com.revature.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Employee;

/**
 * Repository class used for persisting employee data.
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	public Employee findEmployeeById(int id);

	/**
	 * Find all {@link Employee}s by first and last name.
	 * 
	 * @param firstName an {@link Employee} object's first name
	 * @param lastName  an {@link Employee} object's last name
	 * @return a list of {@link Employee}s with the provided first and last names
	 */
	public List<Employee> findAllByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * Find an {@link Employee} by a given email address
	 * 
	 * @param email an {@link Employee} object's email address
	 * @return an {@link Employee} with the provided email address
	 */
	public Employee findByEmail(String email);

}
