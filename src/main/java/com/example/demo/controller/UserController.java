package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.DTO.LoginDTO;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.payload.BaseResponse;
import com.example.demo.service.ComicService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uService;

	@PostMapping("/add") // lấy chi tiết loại sản phẩm
	public String addUser(@RequestBody User user) { // Tao obj moi neu obj request khac entity
		return uService.addUser(user);
	}
	
	@PostMapping("/login") // lấy chi tiết loại sản phẩm
	public LoginResponse loginUser(@RequestBody LoginDTO loginDTO) { // Tao obj moi neu obj request khac entity
		return uService.loginUser(loginDTO);
	}
	
	@GetMapping("/detail/{id}") // lấy chi tiết loại sản phẩm
	public UserDTO loginUser(@PathVariable Long id) { // Tao obj moi neu obj request khac entity
		return uService.getDetailByUserName(id);
	}
}
