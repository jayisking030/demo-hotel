package com.hotel.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.demo.model.AmenityMst;
public interface AmenityMstRepository extends JpaRepository<AmenityMst, Integer> {
	
}
