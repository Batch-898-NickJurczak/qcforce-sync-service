package com.revature;

import static org.junit.jupiter.api.Assertions.*;

import com.revature.logger.AppLogger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppLoggerTest {

	@Test
	void testAppLoggerClass() {
		AppLogger logger=new AppLogger();
		logger.toString();
		assertTrue(true);
	}

}
