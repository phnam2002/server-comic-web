package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.DTO.LoginDTO;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public String addUser(User user) {
//		User user = new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getFullName(), userDTO.getBirthday(),
//				userDTO.getPhoneNumber(), userDTO.getRole()
////               this.passwordEncoder.encode(userDTO.getPassword())
//		);

		userRepo.save(user);

		return user.getUsername();
	}

	@Override
	public LoginResponse loginUser(LoginDTO loginDTO) {
		String msg = "";
		User user1 = userRepo.findByUsername(loginDTO.getUsername()).orElse(null);
		if (user1 != null) {
			if(loginDTO.getPassword().equals(user1.getPassword())) {
				return new LoginResponse("Login Success", true,user1.getId(),user1.getUsername(),user1.getRole(),user1.getFullName(),user1.getBirthday(),user1.getPhoneNumber());
			} else {
				return new LoginResponse("password Not Match", false,user1.getId(),user1.getUsername(),user1.getRole(),user1.getFullName(),user1.getBirthday(),user1.getPhoneNumber());
			}
		} else {
			return new LoginResponse("User not exits", false,user1.getId(),user1.getUsername(),user1.getRole(),user1.getFullName(),user1.getBirthday(),user1.getPhoneNumber());
		}
	}

	@Override
	public UserDTO getDetailByUserName(Long id) {
		User user1 = userRepo.findById(id).orElse(null);
		UserDTO uDTO = new UserDTO();
		return 	uDTO.dataToDTO(user1);
	}

}
