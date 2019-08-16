package com.alltecnologia.locadora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alltecnologia.locadora.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	 Optional<User> findByName(String username);
	 
	 Optional<User> findByEmail(String email);

	 
	 User findByIdUser(Integer id);
	 
	 User findByEmailIgnoreCase(String email);

}