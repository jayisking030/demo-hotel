package com.hotel.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.exception.ExceptionHandling;
import com.hotel.demo.model.AmenityMst;
import com.hotel.demo.model.HotelAmenities;
import com.hotel.demo.model.ResponseHandler;
import com.hotel.demo.model.RoomAmenities;
import com.hotel.demo.service.HotelAmenitiesService;

@RestController
public class AmenitiesController extends ExceptionHandling{

	@Autowired
	HotelAmenitiesService hotelAmenitiesService;

	
	/**
	 * Get All Hotel Amenities
	 * @return Record for all Amenity
	 */
	@GetMapping("/amenities/hotelall")
	public ResponseEntity<Object> getAllAmentiesHotel() {
		try {
			List<HotelAmenities> response = hotelAmenitiesService.findAllAmenties();

			if (!CollectionUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data for Hotel", HttpStatus.NOT_FOUND,
						response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	
	/***
	 * Get Hotel Amenities by Hotel Name
	 * @param hotelName
	 * @return Hotel Amenity Record
	 */
	@GetMapping("/amenities/hotel")
	public ResponseEntity<Object> getAllAmentiesByHotel(@RequestParam(required = true) String hotelName) {
		try {
			Map<String, Object> response = hotelAmenitiesService.findAmnByHotelName(hotelName);

			if (!CollectionUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data for Hotel", HttpStatus.NOT_FOUND,
						response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	
	/***
	 * Get Amenities for a Room by Room Id
	 * @param roomId
	 * @return Room Amenity Record
	 */
	@GetMapping("/amenities/room")
	public ResponseEntity<Object> getAllAmentiesByRoom(@RequestParam(required = false) int roomId) {
		try {
			Map<String, Object> response = hotelAmenitiesService.findAmnByRoom(roomId);

			if (!CollectionUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data for Hotel", HttpStatus.NOT_FOUND,
						response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/***
	 * Insert to Hotel Amenity
	 * @param hotelAmenities
	 * @return Update Hotel Amenity Record
	 */
	@PostMapping("/amenities/hotel")
	public ResponseEntity<Object> saveHotelAmenities(@Valid @RequestBody HotelAmenities hotelAmenities) {
		try {
			HotelAmenities response = hotelAmenitiesService.saveHotelAmn(hotelAmenities);

			if (!ObjectUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data for Hotel", HttpStatus.NOT_FOUND,
						response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/***
	 * Insert to Room Amenity
	 * @param roomAmenities
	 * @return Inserted Room Amenity Record
	 */
	@PostMapping("/amenities/room")
	public ResponseEntity<Object> saveRoomAmenities(@Valid @RequestBody RoomAmenities roomAmenities) {
		try {
			RoomAmenities response = hotelAmenitiesService.saveRoomAmn(roomAmenities);

			if (!ObjectUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data for Hotel", HttpStatus.NOT_FOUND,
						response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	
	/***
	 * Delete Hotel Amenity Record
	 * @param id
	 * @return  Status code ok
	 */
	@DeleteMapping("/amenities/hotel/{id}")
	public ResponseEntity<Object> deleteAmnHotel(@PathVariable("id") int id) {
		try {
			hotelAmenitiesService.deleteAmnByHotelId(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, "");
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	/***
	 * Delete Room Amenity record
	 * @param id
	 * @return Status Code Ok
	 */
	@DeleteMapping("/amenities/room/{id}")
	public ResponseEntity<Object> deleteAmnRoom(@PathVariable("id") int id) {
		try {
			hotelAmenitiesService.deleteAmnByRoomId(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, "");
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/***
	 * Get Room rate plan for 
	 * @param hotelId
	 * @param roomId
	 * @return Calculated room rate
	 */
	@GetMapping("/amenities/roomrate")
	public ResponseEntity<Object> getAllRoomRate(@RequestParam(required = true) int hotelId, int roomId) {
		try {
			double response = hotelAmenitiesService.findRoomRate(hotelId, roomId);

			if (response > 0) {
				return ResponseHandler.generateResponse("Room rate amount!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve rate for Room", HttpStatus.NOT_FOUND,
						response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/***
	 * Get All Records from Amenity_MST Table
	 * @return Amenity MST record
	 */
	@GetMapping("/amenities")
	public ResponseEntity<Object> getAllAminities() {
		try {
			List<AmenityMst> response = hotelAmenitiesService.findAllAmenities();

			if (!CollectionUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data!", HttpStatus.NOT_FOUND, response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/***
	 * Insert Record to Amenity MST table
	 * @param amenityMst
	 * @return Insert Amenity Mst record
	 */
	@PostMapping("/amenities")
	public ResponseEntity<Object> saveAmenityMst(@Valid @RequestBody AmenityMst amenityMst) {
		try {
			AmenityMst response = hotelAmenitiesService.saveAmnMst(amenityMst);

			if (!ObjectUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unsuccessful in creating data!", HttpStatus.NOT_FOUND,
						response);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	
	/***
	 * Update Record for Amenity Mst Table
	 * @param id
	 * @param amenityMst
	 * @return Updated Amenity Mst Record
	 */
	@PutMapping("/amenities/{id}")
	public ResponseEntity<Object> updateAmenityMst(@PathVariable("id") int id,@Valid @RequestBody AmenityMst amenityMst) {
		try {
			AmenityMst response = hotelAmenitiesService.updateAmnMst(amenityMst, id);

			if (!ObjectUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unsuccessful in creating data!", HttpStatus.NOT_FOUND,
						response);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	/***
	 * Delete Amenity Mst record
	 * @param id
	 * @return Status Code Ok
	 */
	@DeleteMapping("/amenities/{id}")
	public ResponseEntity<Object> deleteHotel(@PathVariable("id") int id) {
		try {
			hotelAmenitiesService.deleteAmenity(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK,"");
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

}
