/**
 * 
 */
package com.hochschule.digimarkt.controller;

import com.hochschule.digimarkt.entity.Media;
import com.hochschule.digimarkt.entity.Users;
import com.hochschule.digimarkt.model.AddRequest;
import com.hochschule.digimarkt.model.DataSet;
import com.hochschule.digimarkt.model.LoginRequest;
import com.hochschule.digimarkt.repository.UsersRepository;
import com.hochschule.digimarkt.services.DigitalMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest) {

		Users response = digitalMarketService.login(loginRequest);
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

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@GetMapping("/displayAllNonApprovedProducts")
	public ResponseEntity<List<Media>> findAllNonApprovedProducts() {
		List<Media> approvedProducts = digitalMarketService.findAllNonApprovedProducts();
		if (approvedProducts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(approvedProducts, HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@GetMapping("/displayProduct/{mediaId}")
	public ResponseEntity<List<Media>> findById(@PathVariable int mediaId) {
		List<Media> entities = digitalMarketService.findAllByMediaId(mediaId);
		if (entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(entities, HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@GetMapping("/displayProducts")
	public ResponseEntity<List<Media>> findAll() {
		List<Media> entities = digitalMarketService.findAllByMediaId();
		if (entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(entities, HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@PutMapping("/update-flag/{mediaId}")
	public ResponseEntity<String> updateFlag(@PathVariable int mediaId) {
		boolean updated = digitalMarketService.updateFlag(mediaId);

		if (updated) {
			//String response = "Approved Successfully";
			//return new ResponseEntity.ok(response);
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Media not found or already flagged", HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@PostMapping("/addpost")
	public ResponseEntity<String> addPost(@RequestBody AddRequest addRequest){
		String status = digitalMarketService.addPost(addRequest);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}


	@GetMapping("/totalDataSet")
	public ResponseEntity<DataSet> dataSet() {

		DataSet dataSet = digitalMarketService.findTotalDataSet();

		if(dataSet != null){
			return new ResponseEntity<>(dataSet, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/getmyadds/{userId}")
	public ResponseEntity<List<Media>> getMyAdds(@PathVariable int userId) {
		List<Media> mediaList = digitalMarketService.findMyAdds(userId);

		if (mediaList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(mediaList, HttpStatus.OK);
		}
	}
}

	