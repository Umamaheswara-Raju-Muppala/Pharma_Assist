package com.pharma_assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
