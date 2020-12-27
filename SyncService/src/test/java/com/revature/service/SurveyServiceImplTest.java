package com.revature.service;

import com.revature.models.SurveyForm;
import com.revature.repo.SurveyRepo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Tests the {@link SurveyServiceImpl} and contained methods.
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa
 */
@SpringBootTest
public class SurveyServiceImplTest {

    @Autowired
	private SurveyService service;
	
	@MockBean
	private SurveyRepo repo;
	
	private SurveyForm surveyForm;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
    }
    
    /**
     * Add comments to what this tests for each test
     */
    @Test
	void methodYouAreTestingTest_WhatPathIsThisTestTaking() {
    }
}
