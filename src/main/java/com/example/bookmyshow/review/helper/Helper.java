package com.example.bookmyshow.review.helper;


import com.example.bookmyshow.userlogin.repository.UserRepository;
import com.example.bookmyshow.userlogin.schema.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Helper {

    private final UserRepository userRepository;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new RuntimeException("User not authenticated");
        }

        String username = authentication.getName();

        return userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
