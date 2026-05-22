package com.example.bookmyshow.UserLogin.services;


import com.example.bookmyshow.UserLogin.config.SecurityConfig;
import com.example.bookmyshow.UserLogin.dto.LoginRequest;
import com.example.bookmyshow.UserLogin.repository.UserRepository;
import com.example.bookmyshow.UserLogin.dto.UserRequest;
import com.example.bookmyshow.UserLogin.schema.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //signup
    public User signup(UserRequest userRequest){

        Optional<User> existingEmail = userRepository.findByEmail(userRequest.getEmail());
        if(existingEmail.isPresent()){
            throw new RuntimeException("user with email id already exist");
        }

        User createdUser = User.builder()
                        .name(userRequest.getName())
                        .email(userRequest.getEmail())
                        .password(passwordEncoder.encode(userRequest.getPassword()))
                        .createdAt(LocalDateTime.now())
                        .build();

        return userRepository.save(createdUser);
    }

    //login  -- isme toh login karne par kisi dashboard like url pe redirect karna chahiye
    public void login(LoginRequest loginRequest){
        if(loginRequest.getEmail().isEmpty() || loginRequest.getEmail().trim().isBlank()){
            throw new RuntimeException("Incorrect Email!");
        }

        Optional<User> existingUserWithEmail = userRepository.findByEmail(loginRequest.getEmail());

//        now how to authentication

    }



    // get all users
    public List<User> getallusers(){
         return userRepository.findAll();
    }





//    // sign in with google
//    public




}
