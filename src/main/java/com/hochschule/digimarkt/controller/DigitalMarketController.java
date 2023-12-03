/**
 * 
 */
package com.hochschule.digimarkt.controller;

import com.hochschule.digimarkt.controller.model.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dummy")
public class DigitalMarketController {
	@GetMapping("/hello")
	public String hello() {
		return "Successfully completed";
		
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/api/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		// Validate username and password (in a real-world scenario, use proper authentication)
		if (("ashish".equals(loginRequest.getUsername()) && "ashish".equals(loginRequest.getPassword()))
				|| ("hasan".equals(loginRequest.getUsername()) && "hasan".equals(loginRequest.getPassword()))
				|| ("usama".equals(loginRequest.getUsername()) && "usama".equals(loginRequest.getPassword()))
				|| ("bhavin".equals(loginRequest.getUsername()) && "bhavin".equals(loginRequest.getPassword()))
				|| ("theertha".equals(loginRequest.getUsername()) && "theertha".equals(loginRequest.getPassword()))
				|| ("ritty".equals(loginRequest.getUsername()) && "ritty".equals(loginRequest.getPassword()))) {
			String response = "Login successful";
			return ResponseEntity.ok(response);
		} else {
			String response = "Invalid credentials";
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

}

	