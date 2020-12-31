package com.revature.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Batch;

/**
 * Repository class used for persisting batch data.
 */
@Repository
public interface BatchRepo extends JpaRepository<Batch, String> {

	/**
	 * Find a {@link Batch} by batch Id.
	 * 
	 * @param batchId an int representing a batch id
	 * @return a {@link Batch} with the given batch id
	 */
	Batch findByBatchId(int batchId);
	/**
	 * Find a {@link Batch} by batch name.
	 * 
	 * @param batchName a String representing a batch name
	 * @return a {@link Batch} with the given batch name
	 */
	Batch findByBatchName(String batchName);

	/**
	 * Find all {@link Batch}es with a given start date.
	 * 
	 * @param startDate a {@code Date} representing a date
	 * @return a list of {@link Batch}es with the provided start date
	 */
	List<Batch> findAllByStartDate(Date startDate);

	/**
	 * Find all {@link Batch}es with a start date less than the provided date.
	 * 
	 * @param date a {@code Date} representing a date
	 * @return a list of {@link Batch}es with a start date less than the provided
	 *         date
	 */
	List<Batch> findAllByStartDateLessThan(Date date);

	/**
	 * Find all {@link Batch}es with a start date greater than the provided date.
	 * 
	 * @param date a {@code Date} representing a date
	 * @return a list of {@link Batch}es with a start date greater than the provided
	 *         date
	 */
	List<Batch> findAllByStartDateGreaterThan(Date date);

	/**
	 * Find all {@link Batch}es with a start date between than the provided dates.
	 * 
	 * @param dateOne a {@code Date} representing a date
	 * @param dateTwo a {@code Date} representing a date
	 * @return a list of {@link Batch}es with a start date between than the provided
	 *         dates
	 */
	List<Batch> findAllByStartDateBetween(Date dateOne, Date dateTwo);

	/**
	 * Find all {@link Batch}es with a given end date.
	 * 
	 * @param endDate a {@code Date} representing a date
	 * @return a list of {@link Batch}es with the provided end date
	 */
	List<Batch> findAllByEndDate(Date endDate);

	/**
	 * Find all {@link Batch}es with an end date less than the provided date.
	 * 
	 * @param date a {@code Date} representing a date
	 * @return a list of {@link Batch}es with an end date less than the provided
	 *         date
	 */
	List<Batch> findAllByEndDateLessThan(Date date);

	/**
	 * Find all {@link Batch}es with an end date greater than the provided date.
	 * 
	 * @param date a {@code Date} representing a date
	 * @return a list of {@link Batch}es with an end date greater than the provided
	 *         date
	 */
	List<Batch> findAllByEndDateGreaterThan(Date date);

	/**
	 * Find all {@link Batch}es with an end date between than the provided dates.
	 * 
	 * @param dateOne a {@code Date} representing a date
	 * @param dateTwo a {@code Date} representing a date
	 * @return a list of {@link Batch}es with an end date between than the provided
	 *         dates
	 */
	List<Batch> findAllByEndDateBetween(Date dateOne, Date dateTwo);

	/**
	 * Find all {@link Batch}es with a start date greater than a provided date, and
	 * an end date less than a second provided date.
	 * 
	 * @param dateOne a {@code Date} representing a date
	 * @param dateTwo a {@code Date} representing a date
	 * @return a list of {@link Batch}es with a start date greater than a provided
	 *         date, and an end date less than a second provided date
	 */
	List<Batch> findAllByStartDateLessThanAndEndDateGreaterThan(Date dateOne, Date dateTwo);

	/**
	 * Find all {@link Batch}es with a start date less than a provided date, and an
	 * end date greater than a second provided date.
	 * 
	 * @param dateOne a {@code Date} representing a date
	 * @param dateTwo a {@code Date} representing a date
	 * @return a list of {@link Batch}es with a start date less than a provided
	 *         date, and an end date greater than a second provided date
	 */
	List<Batch> findAllByStartDateGreaterThanAndEndDateLessThan(LocalDate dateOne, LocalDate dateTwo);

	/**
	 * Find all {@link Batch}es with the given skill.
	 * 
	 * @param skill a String representing a skill
	 * @return a list of {@link Batch}es with the given skill
	 */
	List<Batch> findAllBatchesBySkill(String skill);

	/**
	 * Find all {@link Batch}es with the given location.
	 * 
	 * @param batchLocation a String representing a location
	 * @return a list of {@link Batch}es with the given location
	 */
	List<Batch> findAllByBatchLocation(String batchLocation);

	/**
	 * Find all {@link Batch}es with the given type.
	 * 
	 * @param batchType a String representing a type
	 * @return a list of {@link Batch}es with the given type
	 */
	List<Batch> findAllByBatchType(String batchType);

	/**
	 * Find all {@link Batch}es with the current week.
	 * 
	 * @param currentWeek a String representing a current week
	 * @return a list of {@link Batch}es with the given current week
	 */
	List<Batch> findAllByCurrentWeek(String currentWeek);

	/**
	 * Find all {@link Batch}es linked with an {@code Employee} with a given ID.
	 * 
	 * @param employeeId an {@code Employee} object's ID
	 * @return a list of {@link Batch}es linked with an {@code Employee} with the
	 *         provided ID
	 */
	List<Batch> findAllByEmployeeBatchesEmployeeEmployeeId(int employeeId);

	/**
	 * Find all {@link Batch}es linked with an {@code Employee} with a given first
	 * and last name.
	 * 
	 * @param firstName an {@code Employee} object's first name
	 * @param lastName  an {@code Employee} object's last name
	 * @return a list of {@link Batch}es linked with an {@code Employee} with the
	 *         provided first and last name
	 */
	List<Batch> findAllByEmployeeBatchesEmployeeFirstNameAndEmployeeBatchesEmployeeLastName(String firstName,
			String lastName);

	/**
	 * Find all {@link Batch}es linked with an {@code Employee} with a given ID and
	 * role.
	 * 
	 * @param employeeId   an {@code Employee} object's ID
	 * @param employeeRole an {@code Employee} object's role
	 * @return a list of {@link Batch}es linked with an {@code Employee} with the
	 *         provided ID and role
	 */
	List<Batch> findAllByEmployeeBatchesEmployeeEmployeeIdAndEmployeeBatchesEmployeeRole(int employeeId,
			String employeeRole);

	/**
	 * Find all {@link Batch}es linked with an {@code Employee} with a given first
	 * name, last name, and role.
	 * 
	 * @param firstName    an {@code Employee} object's first name
	 * @param lastName     an {@code Employee} object's last name
	 * @param employeeRole an {@code Employee} object's role
	 * @return a list of {@link Batch}es linked with an {@code Employee} with the
	 *         provided first name, last name, and role
	 */
	List<Batch> findAllByEmployeeBatchesEmployeeFirstNameAndEmployeeBatchesEmployeeLastNameAndEmployeeBatchesEmployeeRole(
			String firstName, String lastName, String employeeRole);

}

