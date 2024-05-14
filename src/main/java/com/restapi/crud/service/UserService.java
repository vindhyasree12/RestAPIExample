package com.restapi.crud.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.crud.model.User;
import com.restapi.crud.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long userId) {
	
		return userRepository.findById(userId);
	}

	public User save(User user) {
		
		return userRepository.save(user);
	}

	public void delete(User user) {
		
		userRepository.delete(user);
		
	}

}
