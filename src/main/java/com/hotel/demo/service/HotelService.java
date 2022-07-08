package com.hotel.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.hotel.demo.model.Hotels;
import com.hotel.demo.repository.HotelsRepository;

@Service
public class HotelService {


	@Autowired
	HotelsRepository hotelsRepository;

	public Optional<Hotels> findById(int id) {
		return hotelsRepository.findById(id);
	}

	public Map<String, Object> findByHotelName(String hotelName, int page, int size) {

		List<Hotels> hotels = new ArrayList<Hotels>();
		Pageable paging = PageRequest.of(page, size);

		Page<Hotels> pageHotels;

		if (hotelName == null) {
			pageHotels = hotelsRepository.findAll(paging);

		}

		else {
			pageHotels = hotelsRepository.findByHotelName(hotelName, paging);
		}

		hotels = pageHotels.getContent();
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("hotels", hotels);
		response.put("currentPage", pageHotels.getNumber());
		response.put("totalItems", pageHotels.getTotalElements());
		response.put("totalPages", pageHotels.getTotalPages());
		return response;
	}
	
	


	public Map<String, Object> findByCityCode(String hotelName, int page, int size) {

		List<Hotels> hotels = new ArrayList<Hotels>();
		Pageable paging = PageRequest.of(page, size);

		Page<Hotels> pageHotels = hotelsRepository.findByCityCode(hotelName, paging);
		hotels = pageHotels.getContent();

		Map<String, Object> response = new HashMap<>();
		response.put("hotels", hotels);
		response.put("currentPage", pageHotels.getNumber());
		response.put("totalItems", pageHotels.getTotalElements());
		response.put("totalPages", pageHotels.getTotalPages());
		return response;
	}

	public Hotels saveHotel(Hotels hotel) {
		return hotelsRepository.save(new Hotels(hotel.getHotelName(), hotel.getDescription(), hotel.getCityCode()));
	}

	public Optional<Hotels> saveUpdate(Hotels hotel, int id) {
		Optional<Hotels> hotelData = findById(id);
		Hotels _hotel = new Hotels();

		if (hotelData.isPresent()) {
			_hotel = hotelData.get();
			_hotel.setHotelName(hotel.getHotelName());
			_hotel.setDescription(hotel.getDescription());
			_hotel.setCityCode(hotel.getCityCode());
		}
		return Optional.of(hotelsRepository.save(_hotel));
	}
	
	public void deleteHotelById(int id) {
		hotelsRepository.deleteById(id);
	}

}
