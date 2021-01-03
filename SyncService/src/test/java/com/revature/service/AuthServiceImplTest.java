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
	 * Tests verifyJWT method of {@link AuthServiceImpl}
	 * Test is successful if decoded token is verified.
	 * */
	@Test
	void verifyJWTTest_Authenticated() {
		
		token = Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
				.claim("surveyId", 1)
				.claim("batchId", "1")
				.claim("surveySubId", 1)
				.signWith(SignatureAlgorithm.HS256, "secret")
				.compact();

		boolean returned = auth.verifyJWT(token);
		
		assertTrue(returned);
			
		}
	
	/*
	 * Tests verifyJWT method of {@link AuthServiceImpl}
	 * Returns false if SignatureException is thrown and user is unauthenticated.
	 * */
	@Test()
	void verifyJWTTest_NotAuthenticated() {
				
		token = Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
				.claim("surveyId", 1)
				.claim("batchId", "1")
				.claim("surveySubId", 1)
				.signWith(SignatureAlgorithm.HS256, "notSecret")
				.compact();
		
		boolean returned = auth.verifyJWT(token);
		assertFalse(returned);		
		}

	
	/*
	 * Tests getClaim method of of {@link AuthServiceImpl}
	 * Returns the Claims of the token after verifying the user.
	 */
	@Test
	void getClaimTest_CheckItems() {
		
		token = Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
				.claim("surveyId", 1)
				.claim("batchId", "1")
				.claim("surveySubId", 1)
				.signWith(SignatureAlgorithm.HS256, "secret")
				.compact()
				;

		boolean check = auth.verifyJWT(token);
		Map<String, Object> returned = auth.getClaim();
		
		
		assertEquals(1, returned.get("surveyId"), "getClaimTest_CheckItems have encountered an error in fetching info from the claim");
			
		}
}