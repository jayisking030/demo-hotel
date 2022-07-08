package com.hotel.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.demo.model.RoomAmenities;
public interface RoomAmenityRepository extends JpaRepository<RoomAmenities, Integer> {
	
	List<RoomAmenities> findByRoomId(int roomId);
	
}
