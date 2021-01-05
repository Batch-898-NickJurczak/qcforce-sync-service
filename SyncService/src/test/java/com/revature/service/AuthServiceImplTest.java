package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Date;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.util.InvalidBatchIdException;
import com.revature.util.InvalidSurveyIdException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
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
	
	/**
	 * Checks AuthServiceJWT.createToken(surveyId, batchId) with a valid surveyId.
	 * If token is generated with the correct claims for surveyId, batchId, IAT, and
	 * EXP, test passes. For temporal claims (IAT, EXP), a reasonable time range is
	 * checked in lieu of exact values.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void testCreateToken_withValidParameters() throws InterruptedException {

		String batchId = "2010";
		int surveyId = 1;
		Date before = new Date(System.currentTimeMillis());
		Date beforeExp = new Date(System.currentTimeMillis() + 1000 * 60 * 14);
		java.util.concurrent.TimeUnit.SECONDS.sleep(1);
		String token = this.auth.createToken(surveyId, batchId, 1);
		java.util.concurrent.TimeUnit.SECONDS.sleep(1);
		Date after = new Date(System.currentTimeMillis());
		Date afterExp = new Date(System.currentTimeMillis() + 1000 * 60 * 16);
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
					.parseClaimsJws(token).getBody();
			assertEquals((int) claims.get("surveyId"), surveyId);
			assertEquals((int) claims.get("batchId"), batchId);
			assertTrue(claims.getIssuedAt().after(before));
			assertTrue(claims.getIssuedAt().before(after));
			assertTrue(claims.getExpiration().after(beforeExp));
			assertTrue(claims.getExpiration().before(afterExp));
		} catch (MalformedJwtException exception) {
			fail(exception);
		}
	}

	/**
	 * Checks AuthServiceJWT.createToken(surveyId, batchId) with an invalid
	 * surveyId. The expected behavior is the throwing of the exception
	 * InvalidSurveyIdException.
	 */
	@Test
	void testCreateToken_withInvalidSurveyId() {

		String batchId = "2010";
		int surveyId = -1;

		assertThrows(InvalidSurveyIdException.class, () -> auth.createToken(surveyId, batchId, 1));

	}

	/**
	 * Checks AuthServiceJWT.createToken(surveyId, batchId) with an invalid batchId.
	 * The expected behavior is the throwing of the exception
	 * InvalidBatchIdException.
	 */
	@Test
	void testCreateToken_withInvalidBatchId() {

		String batchId = "-1";
		int surveyId = 1;

		assertThrows(InvalidBatchIdException.class, () -> auth.createToken(surveyId, batchId, 1));

	}

}