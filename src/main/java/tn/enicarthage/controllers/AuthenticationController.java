package tn.enicarthage.controllers;

import java.io.IOException;
import java.util.Optional;

import tn.enicarthage.entities.*;
import tn.enicarthage.repositories.TeacherRepository;
import tn.enicarthage.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.dto.AuthenticationRequest;
import tn.enicarthage.dto.AuthenticationResponse;
import tn.enicarthage.utils.JwtUtil;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  TeacherRepository teacherRepository;
	
	public static final String TOKEN_PREFIX="Bearer";
	public static final String HEADER_STRING="Authorization";
	 @PostMapping("/authenticate")
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
	            throws IOException {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
	                    authenticationRequest.getPassword()));
	        } catch (BadCredentialsException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Username or password");
	        } catch (DisabledException disabledException) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is disabled");
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
	        Optional<User> user = userRepository.findFirstByEmail(authenticationRequest.getEmail());
	       
			Optional<Teacher> teacher = teacherRepository.findByEmail(authenticationRequest.getEmail());
	        

	        if (user.isPresent()) {
	            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
	            AuthenticationResponse response = new AuthenticationResponse(jwt, user.get().getId(), user.get().getRole());
	            return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + jwt).body(response);
	        } else if(teacher.isPresent()) {
	        	 final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		            AuthenticationResponse response = new AuthenticationResponse(jwt, teacher.get().getId());
		            return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + jwt).body(response);
	        }
	        
	        else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	        }
	    }
}