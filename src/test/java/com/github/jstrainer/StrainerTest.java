package com.github.jstrainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.github.jstrainer.filter.Alphanum;
import com.github.jstrainer.filter.Numeric;
import com.github.jstrainer.filter.StripTags;
import com.github.jstrainer.filter.ToUpperCase;
import com.github.jstrainer.filter.Trim;

public class StrainerTest {

	private Strainer strainer = new Strainer();

	@Test
	public void filter() {
		Address address = new Address();
		address.setStreetName("*C!o#untry &Street/");
		address.setCity("Poughkeepsie");
		address.setCountry("usa");
		address.setStateOrRegion(" NY ");
		address.setPostcode("12603A");
		address.setBuildingNumber("539");

		Person person = new Person();
		person.setName("<b>Leonardo</b>           ");
		person.setAddress(address);

		strainer.filter(person);

		assertEquals("Leonardo", person.getName());
		assertEquals("12603", person.getAddress().getPostcode());
		assertEquals("Country Street", person.getAddress().getStreetName());
		assertEquals("USA", person.getAddress().getCountry());
		assertEquals("NY", person.getAddress().getStateOrRegion());

		System.out.println(person);
	}

	@Test
	public void filterInvalidvalue() {
		strainer.filter("");
	}

}

class Person {

	@Trim
	@StripTags
	private String name;

	@Filtered
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + "]";
	}

}

class Address {

	@Numeric
	private String postcode;

	@Alphanum(allowSpace = true)
	private String streetName;

	private String buildingNumber;

	private String city;

	@Trim
	private String stateOrRegion;

	@ToUpperCase
	private String country;

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrRegion() {
		return stateOrRegion;
	}

	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [postcode=" + postcode + ", streetName=" + streetName + ", buildingNumber=" + buildingNumber
				+ ", city=" + city + ", stateOrRegion=" + stateOrRegion + ", country=" + country + "]";
	}

}