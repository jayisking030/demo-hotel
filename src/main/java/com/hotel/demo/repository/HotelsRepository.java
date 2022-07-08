package com.hotel.demo.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.demo.model.Hotels;
public interface HotelsRepository extends JpaRepository<Hotels, Integer> {
	
	Page<Hotels> findByHotelName(String hotelName,Pageable pageable);
	Page<Hotels> findByCityCode(String cityCode,Pageable pageable);
	
	Hotels findByHotelName(String hotelName);
	
	
	

}
