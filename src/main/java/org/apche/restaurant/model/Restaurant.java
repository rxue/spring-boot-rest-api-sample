package org.apche.restaurant.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	private String city;
	private String address;
	private String email;
	private String phone;
	private Time open_at;
	private Time close_at;
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Pizza> pizza_menu;

	public Restaurant() {
	}

	public Restaurant(int id, String name, String city, String address, String email, String phone, Date open_at,
			Date close_at) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.open_at = new Time(open_at.getTime());
		this.close_at = new Time(close_at.getTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Time getOpen_at() {
		return open_at;
	}

	public void setOpen_at(Time open_at) {
		this.open_at = open_at;
	}

	public Time getClose_at() {
		return close_at;
	}

	public void setClose_at(Time close_at) {
		this.close_at = close_at;
	}

	// @JsonIgnore
	public List<Pizza> getPizza_menu() {
		return pizza_menu;
	}

	public void setPizza_menu(List<Pizza> pizza_menu) {
		this.pizza_menu = pizza_menu;
	}

}
