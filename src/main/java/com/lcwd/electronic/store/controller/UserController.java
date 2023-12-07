package com.lcwd.electronic.store.controller;

import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;


    //Create-------------------------------------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }


    //Update------------------------------------------------------------------------------------------------------
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId ,@RequestBody UserDto userDto)
    {
        UserDto userDto2 = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto2, HttpStatus.OK);
    }



    //Delete----------------------------------------------------------------------------------------------------
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId)
    {
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder().message("User is deleted Successfully !!").success(true).status(HttpStatus.OK).build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    //Get All--------------------------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }


    //Get Single--------------------------------------------------------------------------------------------
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId)
    {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }


    //Get by email-------------------------------------------------------------------------------------------
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
    {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }


    //Search User-------------------------------------------------------------------------------------------
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){
        return new ResponseEntity<>(userService.searchUser(keywords), HttpStatus.OK);
    }


}
