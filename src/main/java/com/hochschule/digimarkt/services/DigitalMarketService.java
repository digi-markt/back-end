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

        Users users = usersRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if(null != users) {
            return "Login successful";
        } else {
            throw new IllegalStateException("Incorrect login credentials");
        }
    }

    public Users signUp(Users user) {


        user.setRoleId(1);
        Users savedUser = usersRepository.save(user);
        return savedUser;
    }

}
