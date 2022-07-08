package com.hotel.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.hotel.demo.model.AmenityMst;
import com.hotel.demo.model.HotelAmenities;
import com.hotel.demo.model.Hotels;
import com.hotel.demo.model.RoomAmenities;
import com.hotel.demo.model.Rooms;
import com.hotel.demo.repository.AmenityMstRepository;
import com.hotel.demo.repository.HotelAmenityRepository;
import com.hotel.demo.repository.HotelsRepository;
import com.hotel.demo.repository.RoomAmenityRepository;
import com.hotel.demo.repository.RoomsRepository;

@Service
public class HotelAmenitiesService {

	@Autowired
	HotelAmenityRepository hotelAmenityRepository;

	@Autowired
	HotelsRepository hotelsRepository;

	@Autowired
	AmenityMstRepository amenityMstRepository;

	@Autowired
	RoomsRepository roomsRepository;

	@Autowired
	RoomAmenityRepository roomAmenityRepository;

	public List<HotelAmenities> findAllAmenties() {
		return hotelAmenityRepository.findAll();
	}

	public Map<String, Object> findAmnByHotelName(String hotelName) {
		Hotels hotel = hotelsRepository.findByHotelName(hotelName);

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (!ObjectUtils.isEmpty(hotel)) {
			List<HotelAmenities> hotelAmenitiesList = hotelAmenityRepository.findByHotelId(hotel.getId());
			if (!CollectionUtils.isEmpty(hotelAmenitiesList)) {
				List<Integer> fieldList = hotelAmenitiesList.stream().map(HotelAmenities::getAmenityId)
						.collect(Collectors.toList());
				if (!CollectionUtils.isEmpty(fieldList)) {
					List<AmenityMst> aminetyMstList = amenityMstRepository.findAllById(fieldList);
					map.put("hotelName", hotelName);
					map.put("Aminties", aminetyMstList);

				}
			}
		}
		return map;
	}

	public Map<String, Object> findAmnByRoom(int roomId) {
		Optional<Rooms> room = roomsRepository.findById(roomId);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (room.isPresent()) {
			List<RoomAmenities> roomAmenitiesList = roomAmenityRepository.findByRoomId(room.get().getRoomId());
			if (!CollectionUtils.isEmpty(roomAmenitiesList)) {
				List<Integer> fieldList = roomAmenitiesList.stream().map(RoomAmenities::getAmenityId)
						.collect(Collectors.toList());
				if (!CollectionUtils.isEmpty(fieldList)) {
					List<AmenityMst> aminetyMstList = amenityMstRepository.findAllById(fieldList);
					map.put("Room Id", room.get().getRoomId());
					map.put("Room Desc", room.get().getDescription());
					map.put("Aminties", aminetyMstList);
				}
			}
		}
		return map;
	}

	public HotelAmenities saveHotelAmn(HotelAmenities hotelAmenities) {
		return hotelAmenityRepository.save(new HotelAmenities(hotelAmenities.getHotelId(),
				hotelAmenities.getAmenityId(), hotelAmenities.isChargeable(), hotelAmenities.getAmount()));
	}

	public RoomAmenities saveRoomAmn(RoomAmenities roomAmenities) {
		return roomAmenityRepository.save(new RoomAmenities(roomAmenities.getRoomId(), roomAmenities.getAmenityId(),
				roomAmenities.isChargeable(), roomAmenities.getAmount()));
	}

	public void deleteAmnByHotelId(int id) {
		hotelAmenityRepository.deleteById(id);

	}

	public void deleteAmnByRoomId(int id) {
		roomAmenityRepository.deleteById(id);
	}

	public double findRoomRate(int hotelId, int roomId) {
		Rooms room = roomsRepository.findByRoomIdAndHotelId(hotelId, roomId);
		double amount = 0;
		if (!ObjectUtils.isEmpty(room)) {
			List<RoomAmenities> roomAmenitiesList = roomAmenityRepository.findByRoomId(roomId);
			List<HotelAmenities> hotelAmenitiesList = hotelAmenityRepository.findByHotelId(hotelId);

			double sumRoom = roomAmenitiesList.stream().filter(o -> o.isChargeable() == true)
					.mapToDouble(o -> o.getAmount()).sum();
			double sumHotel = hotelAmenitiesList.stream().filter(o -> o.isChargeable() == true)
					.mapToDouble(o -> o.getAmount()).sum();
			System.out.println(sumRoom);
			System.out.println(sumHotel);
			amount = sumHotel + sumRoom;
		}

		return amount;
	}

	public List<AmenityMst> findAllAmenities() {
		return amenityMstRepository.findAll();

	}

	public AmenityMst saveAmnMst(AmenityMst amenityMst) {
		return amenityMstRepository.save(new AmenityMst(amenityMst.getShortDesc(), amenityMst.getDescription()));
	}

	public AmenityMst updateAmnMst(AmenityMst amenityMst, int amenityId) {
		Optional<AmenityMst> amenityMstdata = amenityMstRepository.findById(amenityId);
		AmenityMst _amenityMst = new AmenityMst();
		if (amenityMstdata.isPresent()) {
			_amenityMst = amenityMstdata.get();
			_amenityMst.setShortDesc(amenityMst.getShortDesc());
			_amenityMst.setDescription(amenityMst.getDescription());
		}
		return amenityMstRepository.save(_amenityMst);
	}

	public void deleteAmenity(int id) {
		amenityMstRepository.deleteById(id);
		
	}

}
