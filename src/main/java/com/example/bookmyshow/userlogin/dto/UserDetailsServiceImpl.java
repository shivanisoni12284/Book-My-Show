package com.example.bookmyshow.userlogin.dto;

import com.example.bookmyshow.userlogin.repository.UserRepository;
import com.example.bookmyshow.userlogin.schema.User;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("user not found"));

         return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).build();
    }
}
