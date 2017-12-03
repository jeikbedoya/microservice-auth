package com.jeikode.microserviceauth.services;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jeikode.microserviceauth.model.User;

public class JwtService {
	
	 
	
	public String createToken(User user) {
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.HOUR, 12);
		Date dateExpirate = cal.getTime();
		
		Algorithm algorithm = null;
		
		try {
			algorithm = Algorithm.HMAC256("clave del token");
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return JWT.create()
				.withIssuedAt(now)
				.withExpiresAt(dateExpirate)
				.withSubject(user.getEmail())
				.sign(algorithm);
				
		
	}

}
