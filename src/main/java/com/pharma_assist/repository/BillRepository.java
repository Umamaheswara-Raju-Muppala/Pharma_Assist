package com.pharma_assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, String> {

}
