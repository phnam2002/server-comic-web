package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.User;
import com.example.demo.model.DTO.LoginDTO;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.response.LoginResponse;

public interface UserService {

	String addUser(User user);
	 
    LoginResponse loginUser(LoginDTO loginDTO);
    
    UserDTO getDetailByUserName(Long id);
}
