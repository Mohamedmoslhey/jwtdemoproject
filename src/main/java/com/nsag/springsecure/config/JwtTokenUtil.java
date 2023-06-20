package com.nsag.springsecure.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	@Value("${jwt.secret}")
	private String secret;
	
	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	private <T>T getClaimfromToken(String token,Function<Claims, T> claimRoselver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimRoselver.apply(claims);
	}

	public String getUserFromToken(String token){
		return getClaimfromToken(token,Claims::getSubject);
	}
	
	public Date getExpireDateFromToken(String token){
		return getClaimfromToken(token,Claims::getExpiration);
	}
	
	private boolean isTokenExpire(String token){
		final Date expireDate = getExpireDateFromToken(token);
		return expireDate.before(new Date());
	}
	//validate token
			public Boolean validateToken(String token, UserDetails userDetails) {
				final String username = getUserFromToken(token);
				return (username.equals(userDetails.getUsername()) && !isTokenExpire(token));
			}
			private String doGenerateToken(Map<String, Object> claims, String subject) {
				// TODO Auto-generated method stub
				return Jwts.builder().setClaims(claims).setSubject(subject)
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
						.signWith(SignatureAlgorithm.HS512, secret).compact();
			}
	//generate token for user
		public String generateToken(UserDetails userDetails) {
			Map<String, Object> claims = new HashMap<>();
			return doGenerateToken(claims, userDetails.getUsername());
		}

		
		
}
