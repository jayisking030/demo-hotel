package com.hotel.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.demo.model.Rooms;

public interface RoomsRepository extends JpaRepository<Rooms, Integer>{
	
	
	List<Rooms> findByHotelId (int hotelId);
	
	Rooms findByRoomIdAndHotelId (int roomId, int hotelId);
	
	

}
