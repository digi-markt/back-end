package com.hochschule.digimarkt.repository;

import com.hochschule.digimarkt.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    List<Media> findAllByMediaId(int mediaId);

    @Query("SELECT m FROM Media m WHERE m.flag = false or reportFlag = true")
    List<Media> findAllNonApprovedProducts();

    @Query("Select m FROM Media m WHERE m.flag = true and reportFlag = false")
    List<Media> findAllByFlagTrue();

    @Query("Select COUNT(m) FROM Media m WHERE m.flag = false")
    int findNumberofNotApprovedAdds();

    @Query("Select COUNT(m) FROM Media m WHERE m.flag = true ")
    int findNumberApprovedAdds();

    @Query("Select m FROM Media m WHERE m.sellerId =:sellerId")
    List<Media> findAddsBySellerId(int sellerId);

    Long countBySellerId(int sellerId);
}
