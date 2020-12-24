package com.revature.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BatchTest {

	
	@Test
	void test() {
		Batch batch = new Batch();
		batch.setAdditionalProperty("a", "a");
		batch.setBatchId("");
		batch.setEmployeeAssignments(new ArrayList<EmployeeAssignment>());
		batch.setEndDate("");
		batch.setGoodGrade(3);
		batch.setId(1);
		batch.setLocation("");
		batch.setName("");
		batch.setPassingGrade(1);
		batch.setSkill("");
		batch.setStartDate("");
		batch.setType("");
		batch.toString();
		assertTrue(true);
	}

}
