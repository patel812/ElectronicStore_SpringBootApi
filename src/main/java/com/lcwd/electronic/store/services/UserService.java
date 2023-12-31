package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;

import java.util.List;

public interface UserService {

    //Create
    UserDto createUser(UserDto userDto);


    //Update
    UserDto updateUser(UserDto userDto, String userId);


    //delete
    void deleteUser(String userId);


    //Get All users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);


    //Get Single User by Id
    UserDto getUserById(String userId);


    //Get single User by email
    UserDto getUserByEmail(String email);


    //Search User
    List<UserDto> searchUser(String keyword);

    //Other user specific features
}
