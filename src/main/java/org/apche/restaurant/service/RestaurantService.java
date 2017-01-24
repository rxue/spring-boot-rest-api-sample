package org.apche.restaurant.service;

import java.util.List;

import org.apche.restaurant.jparepository.RestaurantJpaRepository;
import org.apche.restaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantJpaRepository restaurantRepository;
	public List<Restaurant> getAllRestaurants() {
		return this.restaurantRepository.findAllRestaurantsWithoutPizzaMenu();
	}

	public Restaurant getRestaurantWithAllPizzasById(Integer id) {
		Restaurant restaurant = this.restaurantRepository.findOne(id);
		return restaurant;
	}
	public Restaurant createRestaurant(Restaurant restaurant) {
		if (restaurant.getId() != 0) return null;
		return this.restaurantRepository.save(restaurant);
	}
	public Restaurant updateRestaurant(Restaurant restaurant) {
		if (this.restaurantRepository.findOne(restaurant.getId()) == null)
			return null;
		return this.restaurantRepository.save(restaurant);
	}
	public void deleteRestaurant(int restaurantId) {
		this.restaurantRepository.delete(restaurantId);
	}
}
