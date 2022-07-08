package com.hotel.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.hotel.demo.model.ResponseHandler;
import com.hotel.demo.model.Rooms;
import com.hotel.demo.service.RoomsService;

@RestController
public class RoomController extends ExceptionHandling{

	@Autowired
	RoomsService roomsService;
	
	
	/**
	 * Get All Rooms by Room Id
	 * @param id
	 * @return Room Record
	 */
	@GetMapping("/rooms/{id}")
	public ResponseEntity<Object> getRoomsByHotelId(@PathVariable("id") int id) {
		Optional<List<Rooms>> roomsData = roomsService.findByHotelId(id);

		try {
			if (roomsData.isPresent()) {

				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, roomsData.get());
			} else {
				return ResponseHandler.generateResponse("Unable to locate data of hotel id: " + id,
						HttpStatus.NOT_FOUND, null);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

	}

	/**
	 * Get All Rooms by Hotel
	 * @param hotelName
	 * @return Room Record
	 */
	@GetMapping("/rooms/hotel")
	public ResponseEntity<Object> getRoomByHotelName(@RequestParam(required = true) String hotelName) {
		try {
			Map<String, Object> response = roomsService.findByHotelName(hotelName);

			if (!CollectionUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data for Hotel [" + hotelName + "]",
						HttpStatus.NOT_FOUND, response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/**
	 * Get All Room Record
	 * @return List of Hotel records
	 */
	@GetMapping("/rooms")
	public ResponseEntity<Object> getAllRoom() {
		try {
			List<Rooms> response = roomsService.findAllRooms();

			if (!CollectionUtils.isEmpty(response)) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
			} else {
				return ResponseHandler.generateResponse("Unable to retrieve data", HttpStatus.NOT_FOUND, response);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	//Sav
	/**
	 * Save Room Record
	 * @param room
	 * @return Save Record
	 */
	@PostMapping("/rooms")
	public ResponseEntity<Object> saveRoom(@Valid @RequestBody Rooms room) {
		try {
			Rooms _room = roomsService.saveRoom(room);
			
			if(!ObjectUtils.isEmpty(_room)) {
				return ResponseHandler.generateResponse("Successfully created data!", HttpStatus.CREATED, _room);
			}else {
				return ResponseHandler.generateResponse("Unsuccessful in creating data!", HttpStatus.CREATED, _room);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	//update
	/**
	 * Update Room Record
	 * @param room
	 * @return updated record
	 */
	@PutMapping("/rooms")
	public ResponseEntity<Object> updateRoom(@Valid @RequestBody Rooms room) {
		try {
			Rooms _room = roomsService.updateRoom(room);

			if (!ObjectUtils.isEmpty(_room)) {
				return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, _room);
			} else {
				return ResponseHandler.generateResponse(
						"No data updated! Room id[" + room.getRoomId() + "] does not exist", HttpStatus.NOT_FOUND,
						_room);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	/**
	 * Delete Room record
	 * @param id
	 * @return Status code ok
	 */
	@DeleteMapping("/rooms/{id}")
	public ResponseEntity<Object> deleteHotel(@PathVariable("id") int id) {
		try {
			roomsService.deleteHotelById(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK,"");
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}


}
