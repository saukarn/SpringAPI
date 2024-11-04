package com.conduent.siebel.usps.address.usps;

import com.fasterxml.jackson.annotation.JsonProperty;

public class USPSAddressObject {

	String streetAddress;
	String streetAddressAbbreviation;
	String secondaryAddress;
	String city;
	String cityAbbreviation;
	String state;
	String postalCode;
	String province;
	@JsonProperty("ZIPCode")
	String zipcode;
	@JsonProperty("ZIPPlus4")
	String zipPlus4;
	String urbanization;
	String country;
	String countryISOCode;

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetAddressAbbreviation() {
		return streetAddressAbbreviation;
	}

	public void setStreetAddressAbbreviation(String streetAddressAbbreviation) {
		this.streetAddressAbbreviation = streetAddressAbbreviation;
	}

	public String getSecondaryAddress() {
		return secondaryAddress;
	}

	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityAbbreviation() {
		return cityAbbreviation;
	}

	public void setCityAbbreviation(String cityAbbreviation) {
		this.cityAbbreviation = cityAbbreviation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getZipPlus4() {
		return zipPlus4;
	}

	public void setZipPlus4(String zipPlus4) {
		this.zipPlus4 = zipPlus4;
	}

	public String getUrbanization() {
		return urbanization;
	}

	public void setUrbanization(String urbanization) {
		this.urbanization = urbanization;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryISOCode() {
		return countryISOCode;
	}

	public void setCountryISOCode(String countryISOCode) {
		this.countryISOCode = countryISOCode;
	}

	@Override
	public String toString() {
		return "USPSAddressObject [streetAddress=" + streetAddress + ", streetAddressAbbreviation="
				+ streetAddressAbbreviation + ", secondaryAddress=" + secondaryAddress + ", city=" + city
				+ ", cityAbbreviation=" + cityAbbreviation + ", state=" + state + ", postalCode=" + postalCode
				+ ", province=" + province + ", zipcode=" + zipcode + ", zipPlus4=" + zipPlus4 + ", urbanization="
				+ urbanization + ", country=" + country + ", countryISOCode=" + countryISOCode + "]";
	}

	

}
