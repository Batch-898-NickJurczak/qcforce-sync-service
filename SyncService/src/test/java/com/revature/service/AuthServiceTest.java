package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;



import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@SpringBootTest
class AuthServiceImplTest {
	
	private String token;
	
	private AuthServiceImpl auth = new AuthServiceImpl("secret");
	
	/*
	 * Tests decodeJWT method of {@link AuthServiceImpl}
	 * Test is successful if decoded token has expected value.
	 * */
	@Test
	void decodeJWTTest_Authenticated() {
		
		token = Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
				.claim("surveyId", 1)
				.claim("batchId", "1")
				.claim("surveySubId", 1)
				.signWith(SignatureAlgorithm.HS256, "secret")
				.compact();

		Map<String, Object> returned = auth.decodeJWT(token);
		
		assertEquals(1, returned.get("surveyId"), "decodeJWTTest_Authenticated couldn't authenticate the token");
			
		}
	
	/*
	 * Tests decodeJWT method of {@link AuthServiceImpl}
	 * Returns true if SignatureException is thrown.
	 * */
	@Test()
	void decodeJWTTest_NotAuthenticated() {
				
		token = Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
				.claim("surveyId", 1)
				.claim("batchId", "1")
				.claim("surveySubId", 1)
				.signWith(SignatureAlgorithm.HS256, "notSecret")
				.compact();
		
		boolean thrown = false;
		
		try {
			
			Map<String, Object> returned = auth.decodeJWT(token);
			
		} catch (SignatureException e) {
			thrown = true;
		}
		
		assertTrue(thrown);		
		}

}