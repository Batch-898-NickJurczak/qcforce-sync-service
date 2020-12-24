package com.revature.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeAssignmentTest {

	@Test
	void test() {
		EmployeeAssignment empA = new EmployeeAssignment();
		empA.setAdditionalProperty("", "");
		empA.setDeletedAt(new Object());
		empA.setEmployee(new Employee());
		empA.setRole("");
		empA.toString();
		assertTrue(true);
	}

}
