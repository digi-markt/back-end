package com.hochschule.digimarkt.repository;

import com.hochschule.digimarkt.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    List<Media> findAllByMediaId(int mediaId);

    @Query("SELECT m FROM Media m WHERE m.flag = false")
    List<Media> findAllNonApprovedProducts();

    @Query("Select m FROM Media m WHERE m.flag = true")
    List<Media> findAllByFlagTrue();
}
