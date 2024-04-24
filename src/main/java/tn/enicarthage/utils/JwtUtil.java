package tn.enicarthage.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
	 private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	    private final long expirationTimeMillis = 1000 * 60 * 30; // 30 minutes

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        final Date expiration = extractExpiration(token);
	        return expiration.before(new Date());
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	    }

	    public String generateToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, username);
	    }

	    private String createToken(Map<String, Object> claims, String username) {
	        final Date now = new Date();
	        final Date expirationDate = new Date(now.getTime() + expirationTimeMillis);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(username)
	                .setIssuedAt(now)
	                .setExpiration(expirationDate)
	                .signWith(secretKey)
	                .compact();
	    }

	    public String extractEmail(String token) {
	        // Extraire les claims (informations) du token JWT
	        Claims claims = Jwts.parserBuilder()
	                        .setSigningKey(secretKey)
	                        .build()
	                        .parseClaimsJws(token)
	                        .getBody();

	        // Extraire l'e-mail des claims
	        return claims.getSubject();
	    }
}

