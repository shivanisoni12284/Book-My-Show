package com.example.bookmyshow.UserLogin.services;


import com.example.bookmyshow.UserLogin.config.SecurityConfig;
import com.example.bookmyshow.UserLogin.dto.JWtUtil;
import com.example.bookmyshow.UserLogin.dto.LoginRequest;
import com.example.bookmyshow.UserLogin.repository.UserRepository;
import com.example.bookmyshow.UserLogin.dto.UserRequest;
import com.example.bookmyshow.UserLogin.schema.User;

import com.nimbusds.jwt.JWT;
import com.nimbusds.oauth2.sdk.util.JWTClaimsSetUtils;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JWtUtil jWtUtil;
    private final UserDetailsService userDetailsService;
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
//                        .roles(List.of("ROLE_USER"))
                        .createdAt(LocalDateTime.now())
                        .build();
        // TEMPORARY ROLE ASSIGNMENT
        if(userRequest.getEmail().equals("admin@gmail.com")){
            createdUser.setRoles(List.of("ROLE_ADMIN"));
        }
        else{
            createdUser.setRoles(List.of("ROLE_USER"));
        }

        return userRepository.save(createdUser);
    }

    //login  -- isme toh login karne par kisi dashboard like url pe redirect karna chahiye
    public void login(LoginRequest loginRequest) {
        if (loginRequest.getEmail().isEmpty() || loginRequest.getEmail().trim().isBlank()) {
            throw new RuntimeException("Incorrect Email!");
        }

        Optional<User> existingUserWithEmail = userRepository.findByEmail(loginRequest.getEmail());

        //password checking
        boolean passwordMatched = passwordEncoder.matches(loginRequest.getPassword(), existingUserWithEmail.get().getPassword());

        if (!passwordMatched) {
            throw new RuntimeException("password not matched");
        }
    }

    // get all users
    public List<User> getallusers(){
         return userRepository.findAll();
    }

    //get user by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user with id not found"));
    }


//    // sign in with google
//    public




}
