package com.hotel.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "amenity_mst")
public class AmenityMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "amenity_id")
	private int amenityID;
	
	@NotBlank(message = "Short Description field is mandatory")
	@Column(name = "short_desc")
	private String shortDesc;
	
	@NotBlank(message = "Description field is mandatory")
	@Column(name = "description")
	private String description;

	
	
	

	public AmenityMst( String shortDesc, String description) {
		this.shortDesc = shortDesc;
		this.description = description;
	}

	

	public AmenityMst() {
	}



	public int getAmenityID() {
		return amenityID;
	}


	public void setAmenityId(int amenityID) {
		this.amenityID = amenityID;
	}


	public String getShortDesc() {
		return shortDesc;
	}


	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}	
	
	
	
}
