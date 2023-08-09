package com.example.demo.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.example.demo.common.Constant.FwError;
import com.example.demo.model.Comic;
import com.example.demo.model.User;
import com.example.demo.model.DTO.ComicDTO;
import com.example.demo.model.DTO.LoginDTO;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.payload.BaseResponse;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(ComicServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public BaseResponse addUser(User user) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var userBuilder = User.builder().birthday(user.getBirthday()).fullName(user.getFullName())
					.password(user.getPassword()).phoneNumber(user.getPhoneNumber()).role(user.getRole())
					.username(user.getUsername()).gmail(user.getGmail()).build(); // tiện ích
			jpaExecute(userBuilder);
			baseResponse.setData(userBuilder);

		} catch (Exception e) {
			System.out.println("service");
			System.out.println(e);
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
//			logger.error("error", e);
		}
		return baseResponse;
	}

	@Override
	public LoginResponse loginUser(LoginDTO loginDTO) {
		String msg = "";
		User user1 = userRepo.findByUsername(loginDTO.getUsername()).orElse(null);
		if (user1 != null) {
			if (loginDTO.getPassword().equals(user1.getPassword())) {
				return new LoginResponse("Login Success", true, user1.getId(), user1.getUsername(), user1.getRole(),
						user1.getFullName(), user1.getBirthday(), user1.getPhoneNumber());
			} else {
				return new LoginResponse("password Not Match", false, user1.getId(), user1.getUsername(),
						user1.getRole(), user1.getFullName(), user1.getBirthday(), user1.getPhoneNumber());
			}
		} else {
			return new LoginResponse("User not exits", false, user1.getId(), user1.getUsername(), user1.getRole(),
					user1.getFullName(), user1.getBirthday(), user1.getPhoneNumber());
		}
	}

	@Override
	public BaseResponse delete(Long userId) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var userCur = userRepo.findById(userId).orElse(null);
			if (userCur == null) {
				baseResponse.setFwError(FwError.DLKHONGTONTAI);
				baseResponse.setData(userCur);
				return baseResponse;
			}
			jpaDeleteExecute(userId);
			baseResponse.setData(userCur);
		} catch (Exception e) {
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			logger.error("error", e);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse update(User user) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var userCur = userRepo.findById(user.getId()).orElse(null);
			if (userCur == null) {
				baseResponse.setFwError(FwError.DLKHONGTONTAI);
				return baseResponse;
			}
			userCur.setBirthday(user.getBirthday());
			userCur.setFullName(user.getFullName());
			userCur.setId(user.getId());
			userCur.setPassword(user.getPassword());
			userCur.setPhoneNumber(user.getPhoneNumber());
			userCur.setRole(user.getRole());
			userCur.setUsername(user.getUsername());
			userCur.setGmail(user.getGmail());
			jpaExecute(userCur);
			baseResponse.setData(userCur);

		} catch (Exception e) {
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			System.out.println(e);
//			logger.error("error", e);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse getDetail(Long id) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu

		var userCur = userRepo.findById(id).orElse(null);
		if (userCur == null) {
			baseResponse.setFwError(FwError.DLKHONGTONTAI);
			return baseResponse;
		}
		baseResponse.setData(userRepo.findById(id));
		return baseResponse;
	}

	@Override
	public BaseResponse search(User user, Pageable pageable) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			Specification<User> specification = new Specification<User>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					if (!ObjectUtils.isEmpty(user.getFullName())) {
						predicates.add(
								criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("fullName")),
										"%" + user.getFullName().toUpperCase() + "%")));
					}
					if (!ObjectUtils.isEmpty(user.getUsername())) {
						predicates.add(
								criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("username")),
										"%" + user.getUsername().toUpperCase() + "%")));
					}
					if (!ObjectUtils.isEmpty(user.getRole())) {
						predicates.add(
								criteriaBuilder.and(criteriaBuilder.equal(criteriaBuilder.upper(root.get("role")),
										user.getRole())));
					}
					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			};
			Page<User> page = userRepo.findAll(specification, pageable);
			baseResponse.setData(page);
		} catch (Exception e) {
			System.out.println(e);
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			logger.error("error", e);
		}
		return baseResponse;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void jpaExecute(User user) {
		userRepo.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void jpaDeleteExecute(Long id) {
		userRepo.deleteById(id);
	}
}
