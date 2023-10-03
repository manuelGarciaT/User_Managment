package com.example.class02.controller;

import com.example.class02.model.AppUser;
import com.example.class02.model.Phone;
import com.example.class02.repo.UserRepository;
import com.example.class02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	private final UserService userService;
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@GetMapping
	public List<AppUser> getUserApp() {
		return userService.getAppUser();
	}

	@PostMapping
	public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
		user.setId(null); //ignora id en create user
		userService.saveUserWithPhones(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppUser> getAppUserById(@PathVariable Long id) {
		Optional<AppUser> optionalUser = userService.getAppUserById(id);
		return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PutMapping("/{userId}")
	public void updateUser(@PathVariable Long userId,
						   @RequestParam String name,
						   @RequestParam String email,
						   @RequestParam String password,
						   @RequestBody Phone phone) {
		phone.setId(null); // Ignorar el ID del teléfono
		userService.updateUser(userId, name, email, password, phone);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		Optional<AppUser> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			AppUser user = optionalUser.get();
			userRepository.delete(user);
			return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
	}


}
