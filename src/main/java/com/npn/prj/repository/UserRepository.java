package com.npn.prj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.npn.prj.model.Comic;
import com.npn.prj.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
	
	Optional<User> findByUsername(String username);

	Optional<User> findOneByUsernameAndPassword(String email, String password);
}
