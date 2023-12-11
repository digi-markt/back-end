/**
 * 
 */
package com.hochschule.digimarkt.controller;

import com.hochschule.digimarkt.entity.Users;
import com.hochschule.digimarkt.model.LoginRequest;
import com.hochschule.digimarkt.repository.UsersRepository;
import com.hochschule.digimarkt.services.DigitalMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 
 */
@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52"})
@RestController
@RequestMapping("/dummy")
public class DigitalMarketController {

	private final DigitalMarketService digitalMarketService;

	@Autowired
	private UsersRepository usersRepository;

	public DigitalMarketController(DigitalMarketService digitalMarketService) {
		this.digitalMarketService = digitalMarketService;
	}

	@GetMapping("/hello")
	public String hello() {
		return "Successfully completed";
		
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@PostMapping("/api/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

		String response = digitalMarketService.login(loginRequest);
		return ResponseEntity.ok(response);

	}


	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@PostMapping("/api/signup")
	public ResponseEntity<Users> signup(@RequestBody Users users) {

		if (usersRepository.existsByEmail(users.getEmail())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Users user = digitalMarketService.signUp(users);
		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}



}

	