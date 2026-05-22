package com.example.bookmyshow.UserLogin.controller;


import com.example.bookmyshow.UserLogin.dto.UserRequest;
import com.example.bookmyshow.UserLogin.schema.User;
import com.example.bookmyshow.UserLogin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmyshow")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserRequest userRequest){
        User createdUser = userService.signup(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @GetMapping
    public ResponseEntity<List<User>> getallusers(){
        return  ResponseEntity.ok(userService.getallusers());

    }





}
