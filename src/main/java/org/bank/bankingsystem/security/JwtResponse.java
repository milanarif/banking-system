package org.bank.bankingsystem.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    
    private static final long serialVersionUID = 5926468583005150707L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
