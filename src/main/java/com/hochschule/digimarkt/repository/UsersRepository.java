package com.hochschule.digimarkt.repository;

import com.hochschule.digimarkt.entity.Users;
//Remove the unused imports
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    //Remove the commented lines
    //Optional<Users> findByName(String name);

    //Optional<Users> findByNameAndPassword(String username, String password);

    //@Query("SELECT m FROM Users m WHERE m.email = ?1 and password = ?2")
    Users findByEmailAndPassword(String email, String password);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
