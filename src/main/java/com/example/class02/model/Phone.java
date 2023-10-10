package com.example.class02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonPropertyOrder({ "id", "number", "citycode", "countrycode" })
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Pattern(regexp = "^[0-9]+$", message = "Number is not valid. Only numeric values are allowed.")
	private String number;

	@Pattern(regexp = "^[0-9]+$", message = "CityCode is not valid. Only numeric values are allowed.")
	private String cityCode;

	@Pattern(regexp = "^[0-9]+$", message = "CountryCode is not valid. Only numeric values are allowed.")
	private String countryCode;

	public Phone() {
	}

	public Phone(String number, String cityCode, String countryCode) {
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
