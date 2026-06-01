package com.example.bookmyshow.Review.helper;


import com.example.bookmyshow.UserLogin.repository.UserRepository;
import com.example.bookmyshow.UserLogin.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class Helper {

    private final UserRepository userRepository;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new RuntimeException("User not authenticated");
        }

        String username = authentication.getName();

        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
