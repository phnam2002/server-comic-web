package com.npn.prj.response;

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
public class LoginResponse {
	private String message;
	private boolean status;
	private Long id;
	private String username;
	private String role;
	private String fullName;
	private String birthday;
	private String phoneNumber;
}
