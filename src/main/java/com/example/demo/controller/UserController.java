package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepositories;

@RestController
public class UserController {
	
	@Autowired
	UserRepositories userRepositories;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepositories.findAll();
	}
	
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) {
		userRepositories.save(user);
		return user;
	}
	
	@GetMapping("/user/{name}")
	public List<User> getUser(@PathVariable("name") String name) {
		return userRepositories.findByName(name);
	}
	
	@DeleteMapping("/user/{name}")
	public String deleteUser(@PathVariable("name") String name) {
		List<User> user = userRepositories.findByName(name);
		userRepositories.deleteAll(user);
		return "deleted";
	}
	
	@PutMapping("/user")
	public String updateUser(@RequestBody User user) {
		userRepositories.save(user);
		return "updated";
	}
}
