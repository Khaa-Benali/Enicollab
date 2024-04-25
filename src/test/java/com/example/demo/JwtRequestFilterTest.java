package com.example.demo;


import static org.mockito.Mockito.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import tn.enicarthage.filters.JwtRequestFilter;
import tn.enicarthage.services.jwt.UserDetailsServiceImpl;
import tn.enicarthage.utils.JwtUtil;

import java.io.IOException;

public class JwtRequestFilterTest {

	@Test
	public void testDoFilterInternal() throws ServletException, IOException {
	    // Mock UserDetailsService et JwtUtil
	    UserDetailsServiceImpl userDetailsService = mock(UserDetailsServiceImpl.class);
	    JwtUtil jwtUtil = mock(JwtUtil.class);

	    // Créer une instance de JwtRequestFilter
	    JwtRequestFilter filter = new JwtRequestFilter(userDetailsService, jwtUtil);

	    // Mock HttpServletRequest, HttpServletResponse et FilterChain
	    HttpServletRequest request = mock(HttpServletRequest.class);
	    HttpServletResponse response = mock(HttpServletResponse.class);
	    FilterChain filterChain = mock(FilterChain.class);

	    // Mock username et token
	    String username = "testUser";
	    String token = "mockToken";

	    // Mock UserDetails
	    UserDetails userDetails = User.withUsername(username).password("password").roles("USER").build();

	    // Stubber le comportement de JwtUtil
	    when(jwtUtil.extractUsername(token)).thenReturn(username);
	    when(jwtUtil.validateToken(token, userDetails)).thenReturn(true);

	    // Stubber le comportement de UserDetailsService
	    when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

	    // Simuler les en-têtes de requête
	    when(request.getRequestURI()).thenReturn("/api/test");
	    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

	    // Exécuter la méthode publique de traitement de l'authentification
	    filter.processAuthentication(request, response, filterChain);

	    // Vérifier que l'authentification a été réussie
	    verify(filterChain, times(1)).doFilter(request, response);
	    verify(userDetailsService, times(1)).loadUserByUsername(username);
	    verify(jwtUtil, times(1)).validateToken(token, userDetails);
	}
}