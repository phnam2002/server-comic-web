package com.example.demo.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Long id;

	@Column(name="user_name")
	private String username;

	@Column(name="gmail")
	private String gmail;
	
	private String password;

	@Column(name="full_name")
	private String fullName;
	
	private String birthday;

	@Column(name="phone_number")
	private String phoneNumber;

	private String role;
	
    @Column(name = "CREATED_AT", updatable = false)
    @CreatedDate
    protected Date createdAt;
	
    @Column(name="UPDATED_AT")
    @LastModifiedDate
    protected Date updatedAt;

	public User(String username, String password, String fullName, String birthday, String phoneNumber, String role) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
	
	
}
