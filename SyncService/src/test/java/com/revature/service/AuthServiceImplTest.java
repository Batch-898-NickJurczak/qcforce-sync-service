package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
class AuthServiceImplTest {
	
	private String token;
	
	private AuthServiceImpl auth = new AuthServiceImpl("secret");
	
	@Test
	void decodeJWTTest_Authenticated() {
		
		fail();
		
	}

}
