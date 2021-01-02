package com.revature.service;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class AuthServiceImpl {
	

	private static String secretKey;
	
public AuthServiceImpl(@Value("${token_secret}") String secretKey) {
		AuthServiceImpl.secretKey = secretKey;
	}
	
	public Map<String, Object> decodeJWT(String jwt) {

		//This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
	            .parseClaimsJws(jwt).getBody();
	    Map<String,Object> list = claims;
	    
	    return list;
	}
	
	

}
