package tn.enicarthage.dto;

import tn.enicarthage.enums.UserRole;

public class AuthenticationResponse {

    private String jwtToken;
    private Long id;
    private UserRole role;

    public AuthenticationResponse(String jwtToken,Long id) {
    	 this.jwtToken = jwtToken;
    	 this.id=id;
    }
public AuthenticationResponse() {
        
    }

    public AuthenticationResponse(String jwtToken,Long id,UserRole role) {
        this.jwtToken = jwtToken;
        this.role=role;
        this.id=id;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}