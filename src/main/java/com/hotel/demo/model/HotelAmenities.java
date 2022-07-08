package com.hotel.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "hotel_amenities")
public class HotelAmenities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_ame_id")
	private int hotelAmeId;

	@NotBlank(message = "Hotel id field is mandatory")
	@Column(name = "hotel_id")
	private int hotelId;

	@NotBlank(message = "Amenity Id field is mandatory")
	@Column(name = "amenity_id")
	private int amenityId;

	@Column(name = "chargeable")
	private boolean chargeable;

	@Column(name = "amount")
	private double amount;


	

	public HotelAmenities(int hotelId, int amenityId, boolean chargeable, double amount) {
		this.hotelId = hotelId;
		this.amenityId = amenityId;
		this.chargeable = chargeable;
		this.amount = amount;
	}
	
	

	public HotelAmenities() {
	}



	public int getHotelAmeId() {
		return hotelAmeId;
	}

	public void setHotelAmeId(int hotelAmeId) {
		this.hotelAmeId = hotelAmeId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
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
