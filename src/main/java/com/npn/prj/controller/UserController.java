package com.npn.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.npn.prj.model.User;
import com.npn.prj.model.DTO.LoginDTO;
import com.npn.prj.response.LoginResponse;
import com.npn.prj.response.payload.BaseResponse;
import com.npn.prj.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uService;


	@PostMapping("/login") // lấy chi tiết loại sản phẩm
	public LoginResponse loginUser(@RequestBody LoginDTO loginDTO) { // Tao obj moi neu obj request khac entity
		return uService.loginUser(loginDTO);
	}
	
	@GetMapping("{id}") // lấy chi tiết loại sản phẩm
	public ResponseEntity<BaseResponse> getDetail(@PathVariable Long id) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = uService.getDetail(id);
		return ResponseEntity.ok(baseResponse);
	}

	@GetMapping // Tim kiem theo nhieu dieu kien
	public ResponseEntity<BaseResponse> searchAll(User user, Pageable pageable) {// Tao obj
		BaseResponse baseResponse = uService.search(user, pageable);
		return ResponseEntity.ok(baseResponse);
	}

	@PutMapping("") // Tao moi
	public ResponseEntity<BaseResponse> update(@RequestBody User user) { // Tao obj moi neu obj // request khac
		BaseResponse baseResponse = new BaseResponse();
		baseResponse = uService.update(user);
		return ResponseEntity.ok(baseResponse);
	}

	@PostMapping("/add") // Tao moi
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BaseResponse> create(@RequestBody User user) { // Tao obj moi neu obj // request khac
																					// // entity
		BaseResponse baseResponse = new BaseResponse();
		baseResponse = uService.addUser(user);
		return ResponseEntity.ok(baseResponse);
	}

	@DeleteMapping("{id}") // Xoa
	public ResponseEntity<BaseResponse> delete(@PathVariable long id) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = uService.delete(id);
		return ResponseEntity.ok(baseResponse);
	}
}
