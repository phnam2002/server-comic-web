package com.example.demo.model.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {
	
	private Long id;

	private String userName;

	private String fullName;
	
	private String birthday;

	private String phoneNumber;

	private String role;
	
	public UserDTO dataToDTO(User user) {
		UserDTO uDTO = new UserDTO();
		uDTO.setId(user.getId());
		uDTO.setUserName(user.getUsername());
		uDTO.setFullName(user.getFullName());
		uDTO.setBirthday(user.getBirthday());
		uDTO.setPhoneNumber(user.getPhoneNumber());
		uDTO.setRole(user.getRole());
		return uDTO;
	}
}
