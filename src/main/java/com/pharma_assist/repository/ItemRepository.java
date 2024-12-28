package com.pharma_assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

}
