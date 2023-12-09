package com.hochschule.digimarkt.services;

import com.hochschule.digimarkt.entity.Users;
import com.hochschule.digimarkt.model.LoginRequest;
import com.hochschule.digimarkt.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DigitalMarketService {

    @Autowired
    private UsersRepository usersRepository;

    public String login(LoginRequest loginRequest) {

        Optional<Users> usersOptional = usersRepository.findByNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

        if(usersOptional.isPresent()) {
            return "Login successful";
        } else {
            throw new IllegalStateException("Incorrect login credentials");
        }
    }


}
