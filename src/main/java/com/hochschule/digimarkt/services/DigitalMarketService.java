package com.hochschule.digimarkt.services;

import com.hochschule.digimarkt.entity.Media;
import com.hochschule.digimarkt.entity.Users;
import com.hochschule.digimarkt.model.AddRequest;
import com.hochschule.digimarkt.model.DataSet;
import com.hochschule.digimarkt.model.LoginRequest;
import com.hochschule.digimarkt.repository.MediaRepository;
import com.hochschule.digimarkt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DigitalMarketService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public Users login(LoginRequest loginRequest) {

        Users users = usersRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if(null != users) {
            return users;
        } else {
            throw new IllegalStateException("Incorrect login credentials");
        }
    }

    public Users signUp(Users user) {


        user.setRoleId(1);
        user.setUser_name(user.getUsername());
        user.setRating(0);
        Users savedUser = usersRepository.save(user);
        return savedUser;
    }

    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    public List<Media> findAllByMediaId(int mediaId) {
        return mediaRepository.findAllByMediaId(mediaId);
    }

    public List<Media> findAllByMediaId() {
        return mediaRepository.findAllByFlagTrue();
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

    public String addPost(AddRequest addRequest){
        Media media = new Media();
        media.setName(addRequest.getTitle());
        media.setDescription(addRequest.getDescription());
        media.setFlag(Boolean.FALSE);
        media.setPrice(addRequest.getPrice());
        media.setSellerId(addRequest.getUserId());
        Users user = usersRepository.findById((long)addRequest.getUserId()).orElse(null);
        media.setSeller_name(user.getUsername());
        media.setSeller_email(user.getEmail());
        media.setFree(addRequest.isFree());
        media.setImage(addRequest.getImageUrl());
        media.setCreatedOn(new Date());
        Media media1 = mediaRepository.save(media);
        if (media1 != null){
            return "Saved in the database";
        }
        else {
            return "Not saved in the database";
        }
    }

    public DataSet findTotalDataSet() {
        DataSet dataSet = new DataSet();

        int approvedAdd = 0;
        int pendingAdd = 0;
         approvedAdd = mediaRepository.findNumberApprovedAdds();
         pendingAdd = mediaRepository.findNumberofNotApprovedAdds();
        int totalUser = usersRepository.findAll().size();
        int totalAdd = mediaRepository.findAll().size();

        dataSet.setTotalUser(totalUser);
        dataSet.setApprovedAdd(approvedAdd);
        dataSet.setPendingAdd(pendingAdd);
        dataSet.setTotalAdd(totalAdd);

        return dataSet;

    }

    public List<Media> findMyAdds(int userId)  {

        List<Media> media = mediaRepository.findAddsBySellerId(userId);

        if(media.isEmpty()) {

        }

        return media;

    }

    public boolean deleteMediaById(int mediaId) {
        Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
        if (mediaOptional.isPresent()) {
            mediaRepository.deleteById(mediaId);
            return true;
        }
        return false;
    }
}
