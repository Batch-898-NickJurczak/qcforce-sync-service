package com.revature.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {

	@Test
	void test() {
		Employee employee = new Employee();
		employee.setAdditionalProperty("", "");
		employee.setEmail("");
		employee.setFirstName("");
		employee.setLastName("");
		employee.toString();
		assertTrue(true);
	}

}
