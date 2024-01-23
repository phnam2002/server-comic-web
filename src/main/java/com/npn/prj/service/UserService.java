package com.npn.prj.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.npn.prj.model.Chapter;
import com.npn.prj.model.FollowedComic;
import com.npn.prj.model.User;
import com.npn.prj.model.DTO.LoginDTO;
import com.npn.prj.model.DTO.UserDTO;
import com.npn.prj.response.LoginResponse;
import com.npn.prj.response.payload.BaseResponse;

public interface UserService {

	BaseResponse addUser(User user);
	 
    LoginResponse loginUser(LoginDTO loginDTO);
    
    BaseResponse getDetail(Long id);
    
	BaseResponse delete(Long userId);
	
	BaseResponse update(User user);
	
	BaseResponse search(User user, Pageable pageable);
}
