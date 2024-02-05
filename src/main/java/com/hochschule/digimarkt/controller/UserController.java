package com.hochschule.digimarkt.controller;

import com.hochschule.digimarkt.entity.User;
import com.hochschule.digimarkt.exceptions.UserAlreadyExistException;
import com.hochschule.digimarkt.exceptions.UserNotFoundException;
import com.hochschule.digimarkt.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getall")
    //Remove the declaration of thrown exception
    public ResponseEntity<List<User>> getall() throws IOException {
        try{
            //Replace the type specification in this constructor call with the diamond operator
            return new ResponseEntity<List<User>>(userService.getall(), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    //Remove the declaration of thrown exception
    public ResponseEntity<User> addUser(@RequestBody User user) throws IOException {
        try{
            return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
        }catch (UserAlreadyExistException e){
            return new ResponseEntity("User already exists", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) throws IOException {
        try{
            return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }

}
