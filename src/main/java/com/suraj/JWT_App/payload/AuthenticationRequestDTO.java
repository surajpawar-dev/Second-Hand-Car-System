package com.suraj.JWT_App.payload;

public class AuthenticationRequestDTO {
    private final String username;
    private final String password;

    public AuthenticationRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
