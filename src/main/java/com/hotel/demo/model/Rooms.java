package com.hotel.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;




@Entity
@Table(name= "rooms")
public class Rooms {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	@NotBlank(message = "Roomd id field is mandatory")
	private int roomId;
	
	@NotBlank(message = "Description field is mandatory")
	@Column(name = "description")
	private String description;
	
	
	@Positive(message = "hotel id field is should be any positive number")
	@Column(name = "hotel_id")
	private int hotelId;
	
	
	public Rooms() {
	
	}
	

	public Rooms(String description, int hotelId) {
		this.description = description;
		this.hotelId = hotelId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	
	
	

}
