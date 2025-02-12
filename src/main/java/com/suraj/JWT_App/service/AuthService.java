package com.suraj.JWT_App.service;

import com.suraj.JWT_App.Jwt.JwtProvider;
import com.suraj.JWT_App.entity.Users;
import com.suraj.JWT_App.payload.AuthenticationRequestDTO;
import com.suraj.JWT_App.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public void registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        usersRepository.save(user);
    }

    public String verify(AuthenticationRequestDTO user) {
        Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authenticated.isAuthenticated()) {
            return jwtProvider.generateToken(authenticated.getName());
        }
        return "Invalid user";
    }

    public void registerContentManager(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_CONTENT_MANAGER");
        usersRepository.save(user);

    }

    public void registerBlogManager(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_BLOG_MANAGER");
        usersRepository.save(user);
    }
}
