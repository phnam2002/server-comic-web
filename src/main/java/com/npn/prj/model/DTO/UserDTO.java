package com.npn.prj.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.npn.prj.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {
	
	private Long id;

	private String userName;

	private String fullName;
	
	private String birthday;

	private String phoneNumber;

	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
//	public UserDTO dataToDTO(User user) {
//		UserDTO uDTO = new UserDTO();
//		uDTO.setId(user.getId());
//		uDTO.setUserName(user.getUsername());
//		uDTO.setFullName(user.getFullName());
//		uDTO.setBirthday(user.getBirthday());
//		uDTO.setPhoneNumber(user.getPhoneNumber());
//		uDTO.setRole(user.getRole());
//		return uDTO;
//	}
	
	
}
