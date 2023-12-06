package com.hochschule.digimarkt.controller.repository;

import com.hochschule.digimarkt.controller.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {

}
