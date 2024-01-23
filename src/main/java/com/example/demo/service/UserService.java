package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.Chapter;
import com.example.demo.model.FollowedComic;
import com.example.demo.model.User;
import com.example.demo.model.DTO.LoginDTO;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.payload.BaseResponse;

public interface UserService {

	BaseResponse addUser(User user);
	 
    LoginResponse loginUser(LoginDTO loginDTO);
    
    BaseResponse getDetail(Long id);
    
	BaseResponse delete(Long userId);
	
	BaseResponse update(User user);
	
	BaseResponse search(User user, Pageable pageable);
}
