package com.revature.service;

import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Service
public class AuthServiceImpl {
	
	/*
	 * This string is the key required for decoding JWT
	 * */
	private static String secretKey;
	
	private Map<String, Object> claim;
	
	/*
	 * Assigns the secretKey required to decode JWT. 
	 * Needs to be set as environment variables.
	 * */
	public AuthServiceImpl(@Value("${token_secret}") String secretKey) {
		AuthServiceImpl.secretKey = secretKey;
	}
	
	/*
	 * After Decoding the jwt received from the controller, retrieves the Claims of the token to be used by the system.
	 * @return Map Returns the {@link Map} of jwt claims.
	 * Map contents (keys):
	 *  - "iat" - Issued time of token creation.
	 *  - "exp" - Expiration of token, set to be 15 mins after creation.
	 *  - "surveyId" - ID of survey template being sent out.
	 *  - "surveySubId" - ID of submission of the survey. Will populate only when the user already have taken it.
	 *  - "batchId" - ID of batch who are taking the survey. 
	 * @param jwt String of token.
	 * */
	public Map<String, Object> getClaim() { 
	    return claim;
	}
	
	
	/*
	 * Verifies the jwt token taken inside the URL. If authenticated will store the claim value to be retrieved by getClaims().
	 * @param String  JWT String token to be verified.
	 * @return boolean Returns true if verified, stores the claims decoded into a Map object to be retrieved. Returns false otherwise.
	 */
	public boolean verifyJWT(String jwt) {
		return false;
	}
	
	

}