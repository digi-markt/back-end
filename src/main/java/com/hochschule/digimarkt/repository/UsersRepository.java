package com.hochschule.digimarkt.repository;

import com.hochschule.digimarkt.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    //Optional<Users> findByName(String name);

    //Optional<Users> findByNameAndPassword(String username, String password);

    Users findByUsernameAndPassword(String username, String password);
}
