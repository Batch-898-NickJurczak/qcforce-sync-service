package com.revature.service;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class AuthServiceImpl {
	
	/*
	 * This string is the key required for decoding JWT
	 * */
	private static String secretKey;
	
	/*
	 * Assigns the secretKey required to decode JWT. 
	 * Needs to be set as environment variables.
	 * */
	public AuthServiceImpl(@Value("${token_secret}") String secretKey) {
		AuthServiceImpl.secretKey = secretKey;
	}
	
	/*
	 * Decodes the jwt received from the controller.
	 * @return Map Returns the {@link Map} of jwt claims.
	 * @param jwt String of token.
	 * */
	public Map<String, Object> decodeJWT(String jwt) {
		
	    return null;
	}
	
	

}
