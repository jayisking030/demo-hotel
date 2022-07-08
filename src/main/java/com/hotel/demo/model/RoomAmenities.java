package com.hotel.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "room_amenities")
public class RoomAmenities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_ame_id")
	private int roomAmeId;
	
	@NotBlank(message = "Room Id field is mandatory")
	@Column(name = "room_id")
	private int roomId;
	
	@NotBlank(message = "Amenity field is mandatory")
	@Column(name = "amenity_id")
	private int amenityId;
	
	@Column(name = "chargeable")
	private boolean chargeable;
	
	@Column(name = "amount")
	private double amount;
	
	
	public RoomAmenities(int roomId, int amenityId, boolean chargeable, double amount) {
		this.roomId = roomId;
		this.amenityId = amenityId;
		this.chargeable = chargeable;
		this.amount = amount;
	}

	public RoomAmenities() {
	}


	public int getRoomAmeId() {
		return roomAmeId;
	}

	public void setRoomAmeId(int roomAmeId) {
		this.roomAmeId = roomAmeId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(int amenityId) {
		this.amenityId = amenityId;
	}

	public boolean isChargeable() {
		return chargeable;
	}

	public void setChargeable(boolean chargeable) {
		this.chargeable = chargeable;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
	
	
	
}
