package com.hochschule.digimarkt.controller.repository;

import com.hochschule.digimarkt.controller.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
