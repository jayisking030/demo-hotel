package com.hotel.demo.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.demo.model.HotelAmenities;
public interface HotelAmenityRepository extends JpaRepository<HotelAmenities, Integer> {
	
	
	
	List<HotelAmenities> findByHotelId(int hotelId);
	

	
	
	

}
