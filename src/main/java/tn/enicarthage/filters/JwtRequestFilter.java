package tn.enicarthage.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tn.enicarthage.services.jwt.UserDetailsServiceImpl;
import tn.enicarthage.utils.JwtUtil;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private final UserDetailsServiceImpl userDetailsService;
	private final JwtUtil jwtUtil;
	
	public JwtRequestFilter(UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        String requestURI = request.getRequestURI();
	        if (requestURI.startsWith("/api/admin")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	        String autheader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;
	        if (autheader != null && autheader.startsWith("Bearer")) {
	            token = autheader.substring(7);
	            username = jwtUtil.extractUsername(token);
	        }
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            if (jwtUtil.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null);
	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	    }

}
