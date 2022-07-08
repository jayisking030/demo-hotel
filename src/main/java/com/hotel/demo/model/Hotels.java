package com.hotel.demo.model;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name= "hotels")
public class Hotels {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "Hotel name field is mandatory")
	@Column(name = "hotel_name")
	private String hotelName;
	
	@NotBlank(message = "Descriptiion field is mandatory")
	@Column(name = "description")
	private String description;
	
	@NotBlank(message = "City Code field is mandatory")
	@Column(name = "city_code")
	private String cityCode;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id")
	@JsonIgnore
	private List<Rooms> rooms;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotels")
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_ame_id")
	private List<HotelAmenities> hotelAmenities;


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Hotels(){
	}

	public Hotels(String hotelName, String description, String cityCode) {
		this.hotelName = hotelName;
		this.description = description;
		this.cityCode = cityCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public List<Rooms> getRooms() {
		return rooms;
	}

	public void setRooms(List<Rooms> rooms) {
		this.rooms = rooms;
	}

	
	
	public List<HotelAmenities> getHotelAmenities() {
		return hotelAmenities;
	}

	public void setHotelAmenities(List<HotelAmenities> hotelAmenities) {
		this.hotelAmenities = hotelAmenities;
	}

	@Override
	public String toString() {
		return "Hotels [id=" + id + ", hotelName=" + hotelName + ", description=" + description + ", cityCode="
				+ cityCode + "]";
	}
	
	
	
	

}
