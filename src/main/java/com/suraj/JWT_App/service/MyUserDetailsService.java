package com.suraj.JWT_App.service;

import com.suraj.JWT_App.entity.Users;
import com.suraj.JWT_App.payload.MyUserDetails;
import com.suraj.JWT_App.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository repository;

    public MyUserDetailsService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Attempting to load user by username: " + username);

        Users user = repository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("User not found for username: "+ username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });
        System.out.println("User loaded successfully: "+ user);
        return new MyUserDetails(user);
    }
}
