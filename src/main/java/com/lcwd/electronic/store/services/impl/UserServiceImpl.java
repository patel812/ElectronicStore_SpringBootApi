package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    //Create--------------------------------------------------------------------------------------------------
    @Override
    public UserDto createUser(UserDto userDto) {

        //Generate unique id in string format
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);


        // dto - entity
        User user = dtoToEntity(userDto);

        User savedUser = userRepository.save(user);

        // entity - dto
        UserDto newDto = entityToDto(savedUser);

        return newDto;
    }




    //Update--------------------------------------------------------------------------------------------------
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        //get data by Id  & (Set data than get update data)
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The user Id is not valid Or not found") );

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        //save data
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);

        return updatedDto;
    }




    //Delete-------------------------------------------------------------------------------------------------
    @Override
    public void deleteUser(String userId) {

        //Get data by Id  & (Set data than get update data)
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("The given Id is not reflecting"));

        //delete user
        userRepository.delete(user);

    }



    //Get All User------------------------------------------------------------------------------------------
    @Override
    public List<UserDto> getAllUser(int pageNumber, int pageSize)
    {
        //Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<User> page = userRepository.findAll(pageable);

        List<User> users = page.getContent();

        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtoList;
    }



    //Get Single User----------------------------------------------------------------------------------------
    @Override
    public UserDto getUserById(String userId) {

       User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("The given user id not found"));
       return entityToDto(user);
    }



    //Get User By Email Id-----------------------------------------------------------------------------------
    @Override
    public UserDto getUserByEmail(String email)
    {
       User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("The email Id not found"));

        return entityToDto(user);
    }


    //Search User---------------------------------------------------------------------------------------------
    @Override
    public List<UserDto> searchUser(String keyword)
    {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = (List<UserDto>) users.stream().map(user -> entityToDto(user));
        return dtoList;
    }




    //entityToDto (Method)--------------------------------------------------------------------------------------
    private UserDto entityToDto(User savedUser) {

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .gender(savedUser.getGender())
//                .about(savedUser.getAbout())
//                .imageName(savedUser.getImageName())
//                .build();

        return mapper.map(savedUser, UserDto.class);

    }

    //dtoToEntity (Method)-------------------------------------------------------------------------------------
    private User dtoToEntity(UserDto userDto) {

//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();

        return mapper.map(userDto , User.class);
    }

}
