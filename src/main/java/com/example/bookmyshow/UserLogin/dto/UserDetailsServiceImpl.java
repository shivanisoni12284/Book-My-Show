package com.example.bookmyshow.UserLogin.dto;

import com.example.bookmyshow.UserLogin.repository.UserRepository;
import com.example.bookmyshow.UserLogin.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userrepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userrepository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("user not found"));

         return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).build();
    }
}
