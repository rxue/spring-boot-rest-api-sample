package org.apche.restaurant.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pizza {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RESTAURANT_ID", referencedColumnName="ID", insertable=false, updatable=false)
	@JsonBackReference
	private Restaurant restaurant;
	private int restaurant_id;
	@NotNull
	private String name;
	private char size;
	private String key_ingredients;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSize() {
		return size;
	}
	public void setSize(char size) {
		this.size = size;
	}
	public String getKey_ingredients() {
		return key_ingredients;
	}
	public void setKey_ingredients(String key_ingredients) {
		this.key_ingredients = key_ingredients;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
