package org.apche.restaurant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.sql.Time;
import java.util.List;

import org.apche.restaurant.AbstractTest;
import org.apche.restaurant.jparepository.PizzaJpaRepository;
import org.apche.restaurant.model.Pizza;
import org.apche.restaurant.model.Restaurant;
import org.apche.restaurant.service.RestaurantService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class RestaurantServiceTest extends AbstractTest {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private PizzaJpaRepository pizzaRepository;//used for testing the cascading of delete
	@Test
	public void testGetAllRestaurants() {
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		assertThat(restaurants).isNotNull();
		assertEquals(3, restaurants.size());
	}
	@Test
	public void testGetRestaurantWithAllPizzasById() {
		Restaurant restaurant = restaurantService.getRestaurantWithAllPizzasById(1);
		assertEquals(1, restaurant.getId());
		List<Pizza> pizzas = restaurant.getPizza_menu();
		assertEquals(3, pizzas.size());
	}
	@Test
	public void testCreateRestaurant() {
		long beforeCreateCount = restaurantService.getAllRestaurants().size();
		Restaurant newRestaurant = new Restaurant();
		newRestaurant.setName("new");
		newRestaurant.setOpen_at(Time.valueOf("8:00:00"));
		newRestaurant.setClose_at(Time.valueOf("23:00:00"));
		assertThat(restaurantService.createRestaurant(newRestaurant)).isNotNull();
		assertEquals(1, restaurantService.getAllRestaurants().size() - beforeCreateCount);
		//Test on invalid insert with an Id other than 0
		beforeCreateCount = restaurantService.getAllRestaurants().size();
		newRestaurant = new Restaurant();
		newRestaurant.setId(100);
		newRestaurant.setName("failed");
		newRestaurant.setOpen_at(Time.valueOf("8:00:00"));
		newRestaurant.setClose_at(Time.valueOf("23:00:00"));
		assertThat(restaurantService.createRestaurant(newRestaurant)).isNull();
		assertEquals(restaurantService.getAllRestaurants().size(), beforeCreateCount);
	}
	@Test
	public void testUpdateRestaurant() {
		Restaurant restaurantToUpdate = new Restaurant();
		restaurantToUpdate.setId(1000);
		assertThat(restaurantService.updateRestaurant(restaurantToUpdate)).isNull();
		restaurantToUpdate = new Restaurant();
		restaurantToUpdate.setId(2);
		restaurantToUpdate.setName("modified");
		assertThat(restaurantService.updateRestaurant(restaurantToUpdate)).isNotNull();
		Restaurant updatedRestaurant = restaurantService.getRestaurantWithAllPizzasById(2);
		assertEquals(updatedRestaurant.getName(), restaurantToUpdate.getName());
	}
	@Test
	public void testDeleteRestaurant() {
		long beforeDeletePizzaCount = pizzaRepository.count();
		restaurantService.deleteRestaurant(1);
		assertTrue(beforeDeletePizzaCount > pizzaRepository.count());
	}
}
