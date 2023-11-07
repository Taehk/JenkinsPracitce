package com.playdata.itmeservice.repository;

import com.playdata.itmeservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findItemByProductName(String productName);
}
