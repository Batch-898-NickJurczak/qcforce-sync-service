package com.revature.service;

import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class AuthServiceImpl {
	

	private static String secretKey;
	
public AuthServiceImpl(@Value("${token_secret}") String secretKey) {
		AuthServiceImpl.secretKey = secretKey;
	}
	
	public HashMap<String, Object> decodeJWT(String jwt) {

	    return null;
	}
	
	

}
