package com.hotel.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hotel.demo.model.Hotels;
import com.hotel.demo.model.Rooms;
import com.hotel.demo.repository.HotelsRepository;
import com.hotel.demo.repository.RoomsRepository;

@Service
public class RoomsService {

	@Autowired
	RoomsRepository roomsRepository;

	@Autowired
	HotelsRepository hotelsRepository;

	public Optional<List<Rooms>> findByHotelId(int id) {
		return Optional.of(roomsRepository.findByHotelId(id));
	}

	public List<Rooms> findByHotelIds(int id) {
		return roomsRepository.findByHotelId(id);
	}

	public  Map<String, Object> findByHotelName(String hotelName) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

			Hotels hotel = hotelsRepository.findByHotelName(hotelName);
			if (!ObjectUtils.isEmpty(hotel)) {
				List<Rooms> rooms = hotel.getRooms();
				map.put("hotel", hotel);
				map.put("rooms", rooms);
			}
	
		return map;
	}
	
	
	public List<Rooms> findAllRooms(){
		return roomsRepository.findAll();
	}

	public Rooms saveRoom(Rooms room) {
		return roomsRepository.save(new Rooms(room.getDescription(),room.getHotelId()));
		
	}
	public Rooms updateRoom(Rooms room) {
		
		Optional<Rooms> roomdata = roomsRepository.findById(room.getRoomId());
		Rooms _rooms = null;
		if(roomdata.isPresent()) {
			_rooms = new Rooms();
			_rooms = roomdata.get();
			_rooms.setDescription(room.getDescription());
			_rooms.setHotelId(room.getHotelId());
			
			_rooms=  roomsRepository.save(_rooms);
		}else {
			_rooms = null;
		}
		
		return _rooms;
	}

	public void deleteHotelById(int id) {
		roomsRepository.deleteById(id);
	}

}
