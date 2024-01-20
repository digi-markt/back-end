package com.hochschule.digimarkt.services;

import com.hochschule.digimarkt.entity.Media;
import com.hochschule.digimarkt.entity.Users;
import com.hochschule.digimarkt.exceptions.NotFoundException;
import com.hochschule.digimarkt.model.AddRequest;
import com.hochschule.digimarkt.model.DataSet;
import com.hochschule.digimarkt.model.LoginRequest;
import com.hochschule.digimarkt.repository.MediaRepository;
import com.hochschule.digimarkt.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.hochschule.digimarkt.utility.EncryptUtility.encodePassword;

@Service
public class DigitalMarketService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MediaRepository mediaRepository;


    public Users login(LoginRequest loginRequest) {
        try {
            Users users = usersRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

            if (null != users) {
                return users;
            } else {
                throw new IllegalStateException("Incorrect login credentials");
            }
        } catch (Exception e) {
            throw new IllegalStateException("User not found during login");
        }
    }

    public Users signUp(Users user) {
        try {
            String originalPassword = user.getPassword();
            String encodedPassword = encodePassword(originalPassword);

            user.setPassword(encodedPassword);
            user.setRoleId(1);
            user.setUser_name(user.getUsername());
            user.setRating(0);
            Users savedUser = usersRepository.save(user);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
       throw new IllegalStateException("User with this email already exists.", e);
     } catch (Exception e) {
        throw new IllegalStateException("Error while signing up user", e);
     }
    }

    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    public List<Media> findAllByMediaId(int mediaId) {
        try {
            return mediaRepository.findAllByMediaId(mediaId);
        }catch (NotFoundException e) {
            throw new RuntimeException("Media not found", e);
        }
    }

    public List<Media> findAllByMediaId() {
        try {
            return mediaRepository.findAllByFlagTrue();
        } catch (Exception e) {
           throw new IllegalStateException("Unexpected error during findAllByMediaId", e);
        }
    }

    public List<Media> findAllNonApprovedProducts() {
        try {
            List<Media> approvedProducts = mediaRepository.findAllNonApprovedProducts();
            return approvedProducts;
        }catch (NotFoundException e) {
            throw new IllegalStateException("No media found", e);
        }
        catch (DataAccessException e) {
          throw new IllegalStateException("Error accessing media data", e);
        }
    }

    public boolean updateFlag(int mediaId) {
        try {
            Media media = mediaRepository.findById(mediaId).orElse(null);

            if (media != null) {
                media.setFlag(true);
                mediaRepository.save(media);
                return true;
            }
            return false;
        }catch (DataAccessException e) {
            throw new IllegalStateException("Error accessing media data during flag update", e);
        }catch (Exception e) {
            throw new IllegalStateException("Unexpected error during flag update", e);
        }
    }

    public String addPost(AddRequest addRequest) {
        try {
            Media media = new Media();
            media.setName(addRequest.getTitle());
            media.setDescription(addRequest.getDescription());
            media.setFlag(Boolean.FALSE);
            media.setPrice(addRequest.getPrice());
            media.setSellerId(addRequest.getUserId());
            Users user = usersRepository.findById((long) addRequest.getUserId()).orElse(null);
            media.setSeller_name(user.getUsername());
            media.setSeller_email(user.getEmail());
            media.setFree(addRequest.isFree());
            media.setImage(addRequest.getImageUrl());
            media.setCreatedOn(new Date());
            Media media1 = mediaRepository.save(media);
            if (media1 != null) {
                return "Saved in the database";
            } else {
                return "Not saved in the database";
            }
        }catch (DataAccessException e) {
            throw new IllegalStateException("Error accessing media data during post addition", e);
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected error during post addition", e);
        }
    }

    public DataSet findTotalDataSet() {
        try {
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
        } catch (DataAccessException e) {
            throw new RuntimeException("Error accessing data during total data retrieval", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during total data  retrieval", e);
        }
    }

    public List<Media> findMyAdds(int userId)  {
        try {
            List<Media> media = mediaRepository.findAddsBySellerId(userId);

            if (media.isEmpty()) {
                throw new IllegalStateException("Media not found");
            }
            return media;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error accessing media data during findMyAdds", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during findMyAdds", e);
        }
    }

    public boolean deleteMediaById(int mediaId) {
        try {
            Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
            if (mediaOptional.isPresent()) {
                mediaRepository.deleteById(mediaId);
                return true;
            }
            return false;
        }catch (NotFoundException e) {
            throw new IllegalStateException("Media not found");
        }
    }

    @Transactional
    public boolean setReportFlagTrue(int mediaId, boolean flag) {
        Media media = mediaRepository.findById(mediaId).orElse(null);

        if (media != null) {
            media.setReportFlag(flag);
            mediaRepository.save(media);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean updateMedia(int mediaId,String name, String description, String image, Double price) {
        Media media = mediaRepository.findById(mediaId).orElse(null);

        if (media != null) {
            if (name != null) {
                media.setName(name);
            }
            if (description != null) {
                media.setDescription(description);
            }
            if (image != null) {
                media.setImage(image);
            }
            if (price != null) {
                media.setPrice(price);
            }
            mediaRepository.save(media);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean updateUserProfile(int userId, String username, String email) {
        Users user = usersRepository.findById((long) userId).orElse(null);

        if (user != null) {
            if (username != null) {
                user.setUsername(username);
                user.setUser_name(username);
            }
            if (email != null) {
                user.setEmail(email);
            }
            usersRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public Long countBySellerId(int sellerId) {
        return mediaRepository.countBySellerId(sellerId);
    }
}
