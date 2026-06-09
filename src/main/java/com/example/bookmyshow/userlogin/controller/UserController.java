package com.example.bookmyshow.userlogin.controller;


import com.example.bookmyshow.userlogin.dto.JWtUtil;
import com.example.bookmyshow.userlogin.dto.LoginRequest;
import com.example.bookmyshow.userlogin.dto.UserDetailsServiceImpl;
import com.example.bookmyshow.userlogin.dto.UserRequest;
import com.example.bookmyshow.userlogin.schema.User;
import com.example.bookmyshow.userlogin.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmyshow")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JWtUtil jWtUtil;
    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<User> signup(@RequestBody UserRequest userRequest){
        User createdUser = userService.signup(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @GetMapping("/admin/allusers")
    public ResponseEntity<List<User>> getallusers(){
        return  ResponseEntity.ok(userService.getallusers());

    }

    @GetMapping("/singleUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("LOGIN API HIT");
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            String jwt = jWtUtil.generateToken(userDetails);
            System.out.println(jwt);
            return new ResponseEntity<>(jwt,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception occured while createAuthenticationToken",e);
            return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);
        }
    }





}
