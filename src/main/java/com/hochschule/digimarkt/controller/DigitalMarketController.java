/**
 * 
 */
package com.hochschule.digimarkt.controller;

import com.hochschule.digimarkt.entity.Media;
import com.hochschule.digimarkt.entity.Users;
import com.hochschule.digimarkt.exceptions.InternalException;
import com.hochschule.digimarkt.exceptions.NotAuthorizedException;
import com.hochschule.digimarkt.exceptions.NotFoundException;
import com.hochschule.digimarkt.model.AddRequest;
import com.hochschule.digimarkt.model.DataSet;
import com.hochschule.digimarkt.model.LoginRequest;
import com.hochschule.digimarkt.repository.UsersRepository;
import com.hochschule.digimarkt.services.DigitalMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hochschule.digimarkt.utility.EncryptUtility.encodePassword;

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
		try{
			String originalPassword = loginRequest.getPassword();
			String encodedPassword = encodePassword(originalPassword);

			loginRequest.setPassword(encodedPassword);
			Users response = digitalMarketService.login(loginRequest);

			return ResponseEntity.ok(response);

		} catch (NotAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		} catch (InternalException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@PostMapping("/api/signup")
	public ResponseEntity<Users> signup(@RequestBody Users users) {
		try{
			if (usersRepository.existsByEmail(users.getEmail())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Users user = digitalMarketService.signUp(users);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (NotAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		} catch (InternalException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@GetMapping("/displayAllNonApprovedProducts")
	public ResponseEntity<List<Media>> findAllNonApprovedProducts() {
		try {
			List<Media> approvedProducts = digitalMarketService.findAllNonApprovedProducts();
			if (approvedProducts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(approvedProducts, HttpStatus.OK);
			}
		} catch (InternalException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@GetMapping("/displayProduct/{mediaId}")
	public ResponseEntity<List<Media>> findById(@PathVariable int mediaId) {
		try {
			List<Media> entities = digitalMarketService.findAllByMediaId(mediaId);
			if (entities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(entities, HttpStatus.OK);
			}
		} catch (InternalException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@GetMapping("/displayProducts")
	public ResponseEntity<List<Media>> findAll() {
		try{
			List<Media> entities = digitalMarketService.findAllByMediaId();
			if (entities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(entities, HttpStatus.OK);
			}
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}  catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@PutMapping("/update-flag/{mediaId}")
	public ResponseEntity<String> updateFlag(@PathVariable int mediaId) {
		try {
			boolean updated = digitalMarketService.updateFlag(mediaId);

			if (updated) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Media not found or already flagged", HttpStatus.NOT_FOUND);
			}
		}catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@PostMapping("/addpost")
	public ResponseEntity<String> addPost(@RequestBody AddRequest addRequest){
		try{
			String status = digitalMarketService.addPost(addRequest);
			return new ResponseEntity<>(status, HttpStatus.OK);
		}catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Error: Duplicate entry or data integrity violation"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InternalException e) {
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})

	@GetMapping("/totalDataSet")
	public ResponseEntity<DataSet> dataSet() {
		try {
			DataSet dataSet = digitalMarketService.findTotalDataSet();

			if (dataSet != null) {
				return new ResponseEntity<>(dataSet, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


	@CrossOrigin(origins = {"http://localhost:4200", "http://13.51.149.52", "http://digimarkt.online", "https://digimarkt.online", "http://digimarkt.shop", "https://digimarkt.shop"})
	@GetMapping("/getmyadds/{userId}")
	public ResponseEntity<List<Media>> getMyAdds(@PathVariable int userId) {
		try{
			List<Media> mediaList = digitalMarketService.findMyAdds(userId);

			if (mediaList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(mediaList, HttpStatus.OK);
			}
		}catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/deleteMedia/{mediaId}")
	public ResponseEntity<String> deleteMedia(@PathVariable int mediaId) {
		try {
			boolean deleted = digitalMarketService.deleteMediaById(mediaId);
			if (deleted) {
				return new ResponseEntity<>("Post Removed successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
			}
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting media: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

	