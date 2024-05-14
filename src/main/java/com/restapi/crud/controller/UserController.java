package com.restapi.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.crud.Exceptions.ResourceNotFoundException;
import com.restapi.crud.model.User;
import com.restapi.crud.repository.UserRepository;
import com.restapi.crud.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Optional<User> userOptional = userService.findById(userId);
		User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + userId));
		return ResponseEntity.ok(user);
	}

	@PostMapping("/save/users")
	public User createUser(@Validated @RequestBody User user) {
		return userService.save(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		Optional<User> userOptional = userService.findById(userId);
		User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + userId));
		user.setEmail(userDetails.getEmail());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		final User updatedUser = userService.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Optional<User> userOptional = userService.findById(userId);
		User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + userId));
		userService.delete(user);
		return "Deleted";
	}
}
