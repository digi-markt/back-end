package com.hochschule.digimarkt.controller.repository;

import com.hochschule.digimarkt.controller.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

}
