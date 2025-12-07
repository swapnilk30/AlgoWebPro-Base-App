package com.algowebpro.scm.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.algowebpro.scm.repository.ScmUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomScmUserDetailsService implements UserDetailsService{
    
    private final ScmUserRepository scmUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Loading user by username: {}", username);


        return scmUserRepository.findByEmail(username)
            .orElseThrow(() -> {
                log.error("User not found with username: {}", username);
                return new UsernameNotFoundException("User not found with username: " + username);
            });
    }


    

}
