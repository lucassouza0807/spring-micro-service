package com.dizimo.dizimo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.dizimo.dizimo.models.User;

import com.dizimo.dizimo.dizimoRepository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserDetailService userDetailService;
    
    @Autowired
    private UserRepository user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetails = user.findByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                // Replace with appropriate roles from userDetails if available
                .build();

    }

}
