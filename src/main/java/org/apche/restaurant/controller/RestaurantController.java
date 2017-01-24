package org.apche.restaurant.controller;

import java.util.List;

import org.apche.restaurant.model.Restaurant;
import org.apche.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/rest")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	@RequestMapping("/rest/restaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		return new ResponseEntity<>(this.restaurantService.getAllRestaurants(), HttpStatus.OK);
	}
	@RequestMapping("/rest/restaurant/full/{id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") int id) {
		Restaurant restaurant = this.restaurantService.getRestaurantWithAllPizzasById(id);
		if (restaurant == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(restaurant, HttpStatus.FOUND);
	}
	@RequestMapping(value = "/rest/restaurant", method = RequestMethod.POST)
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant createdRestaurant = this.restaurantService.createRestaurant(restaurant);
		if (createdRestaurant == null)
			return new ResponseEntity<>(createdRestaurant, HttpStatus.BAD_REQUEST);
		else 
			return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/rest/restaurant/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") int id, @RequestBody Restaurant restaurant) {
		restaurant.setId(id);
		Restaurant updatedRestaurant = this.restaurantService.updateRestaurant(restaurant);
		if (updatedRestaurant == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else 
			return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value = "/rest/restaurant/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable("id") Integer id) {
		this.restaurantService.deleteRestaurant(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
