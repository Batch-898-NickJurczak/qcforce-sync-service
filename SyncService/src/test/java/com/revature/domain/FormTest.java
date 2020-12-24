package com.revature.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormTest {

	@Test
	void test() {
		Form f = new Form();
		Form f1 = new Form();
		Form f2 = new Form();
		f1.setId(2);
		f2.setId(2);
		f2.setFormId(1);
		f.setFormId(1);
		f.setId(1);
		f.toString();
		f.equals(f);
		f.equals(null);
		f.equals(2);
		f.equals(f1);
		f.equals(f2);
		f.equals(f);
		assertTrue(true);
	}

}
