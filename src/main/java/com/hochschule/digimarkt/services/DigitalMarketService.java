package com.hochschule.digimarkt.services;

import com.hochschule.digimarkt.entity.Media;
import com.hochschule.digimarkt.entity.Users;
import com.hochschule.digimarkt.model.LoginRequest;
import com.hochschule.digimarkt.repository.MediaRepository;
import com.hochschule.digimarkt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalMarketService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MediaRepository mediaRepository;

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

    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    public List<Media> findAllByMediaId(int mediaId) {
        return mediaRepository.findAllByMediaId(mediaId);
    }

    public List<Media> findAllNonApprovedProducts() {
       List<Media> approvedProducts =  mediaRepository.findAllNonApprovedProducts();
       return approvedProducts;
    }

    public boolean updateFlag(int mediaId) {
        Media media = mediaRepository.findById(mediaId).orElse(null);

        if (media != null) {
            media.setFlag(true);
            mediaRepository.save(media);
            return true;
        }
        return false;
    }
}
