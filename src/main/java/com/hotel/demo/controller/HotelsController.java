package com.hotel.demo.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.hotel.demo.model.Hotels;
import com.hotel.demo.model.ResponseHandler;
import com.hotel.demo.repository.HotelsRepository;
import com.hotel.demo.service.HotelService;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/hotel")
public class HotelsController {

	@Autowired
	HotelsRepository hotelsRepository;

	@Autowired
	HotelService hotelService;

	
	/***
	 * Get Hotels record 
	 * @param id
	 * @return Hotel Record data
	 */
	@GetMapping("/hotels/{id}")
	public ResponseEntity<Object> getHotelById(@PathVariable("id") int id)  {
		Optional<Hotels> hotelData = hotelService.findById(id);
		try {
			if (hotelData.isPresent()) {
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, hotelData.get());
			}else {
				return ResponseHandler.generateResponse("Unable to locate data of hotel id: " + id, HttpStatus.NOT_FOUND, null);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		 
	}


	/***
	 * Get All Hotel record
	 * @param hotelName
	 * @param page
	 * @param size
	 * @return Paginated Hotel Record
	 */
	@GetMapping("/hotels")
	public ResponseEntity<Object> getAllHotels(@RequestParam(required = false) String hotelName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		try {
			Map<String, Object> response = hotelService.findByHotelName(hotelName, page, size);
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	
	/***
	 * Get Hotel record by city code
	 * @param cityCode
	 * @param page
	 * @param size
	 * @return Hotel Record
	 */
	@GetMapping("/hotels/citycode")
	public ResponseEntity<Object> getAllHotelsByCityCode(@RequestParam(required = true) String cityCode,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		try {
			Map<String, Object> response = hotelService.findByCityCode(cityCode, page, size);
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, response);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	
	/***
	 * Get All Hotel Record
	 * @param hotel
	 * @return
	 */
	@PostMapping("/hotels")
	public ResponseEntity<Object> createHotel(@Valid @RequestBody Hotels hotel) {
		try {
			Hotels _hotel = hotelService.saveHotel(hotel);
			if(!ObjectUtils.isEmpty(_hotel)) {
				return ResponseHandler.generateResponse("Successfully created data!", HttpStatus.CREATED, _hotel);
			}else {
				return ResponseHandler.generateResponse("Unsuccessful in creating data!", HttpStatus.CREATED, _hotel);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	
	/***
	 * Insert Hotel record by array of Hotel Record
	 * @param hotel
	 * @return Array of Hotel record
	 */
	@PostMapping("/hotels/batch")
	public ResponseEntity<Object> createHotelBatch(@Valid @RequestBody List<Hotels> hotel) {
		try {
			List<Hotels> _hotel = hotelsRepository.saveAll(hotel);
			return ResponseHandler.generateResponse("Successfully created data!", HttpStatus.CREATED, _hotel);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	
	/***
	 * Update Hotel record
	 * @param id
	 * @param hotel
	 * @return Updated Hotel Record
	 */
	@PutMapping("/hotels/{id}")
	public ResponseEntity<Object> updateHotel(@PathVariable("id") int id, @RequestBody Hotels hotel) {
		Optional<Hotels> hotelData = hotelService.saveUpdate(hotel, id);
		try {
			if (hotelData.isPresent()) {
				return ResponseHandler.generateResponse("Updated!", HttpStatus.OK, hotelData.get());
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
			}
	}

	/***
	 * Delete hotel record
	 * @param id
	 * @return Status coode ok
	 */
	@DeleteMapping("/hotels/{id}")
	public ResponseEntity<Object> deleteHotel(@PathVariable("id") int id) {
		try {
			hotelService.deleteHotelById(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK,"");
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	
}
