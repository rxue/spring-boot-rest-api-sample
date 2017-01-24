package org.apche.restaurant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.apche.restaurant.AbstractTest;
import org.apche.restaurant.jparepository.RestaurantJpaRepository;
import org.apche.restaurant.model.Pizza;
import org.apche.restaurant.model.Restaurant;
import org.apche.restaurant.service.PizzaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class PizzaServiceTest extends AbstractTest {
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private RestaurantJpaRepository restaurantRepository;
	@Test
	public void testGetAllPizzas() {
		List<Pizza> pizzas = pizzaService.getAllPizzas();
		assertThat(pizzas).isNotNull();
		assertEquals(4, pizzas.size());
	}
	@Test
	public void testPizzasByName() {
		List<Pizza> pizzas = pizzaService.getPizzasByName("PIZZA");
		assertThat(pizzas).isNotNull();
		assertEquals(3, pizzas.size());
		pizzas = pizzaService.getPizzasByName("ohmygod");
		assertTrue(pizzas.isEmpty());
	}
	@Test
	public void testGetPizzaById() {
		Pizza pizza = pizzaService.getPizzaById(1);
		assertThat(pizza).isNotNull();
		assertThat(pizza.getRestaurant()).isNotNull();
	}
	@Test
	public void testCreatePizza() {
		Pizza newPizza = new Pizza();
		newPizza.setName("new");
		newPizza.setRestaurant_id(2);
		assertThat(pizzaService.createPizza(newPizza)).isNotNull();
		Restaurant parentRestaurant = restaurantRepository.findOne(2);
		List<Pizza> pizzaList = parentRestaurant.getPizza_menu();
		newPizza = pizzaList.get(pizzaList.size()-1);
		assertEquals("new", newPizza.getName());//confirm that the parent restaurant contains the new added pizza
		//Test on invalid insert with an Id other than 0
		newPizza = new Pizza();
		newPizza.setId(100);
		newPizza.setName("failed");
		assertThat(pizzaService.createPizza(newPizza)).isNull();
		
	}
	@Test
	public void testUpdatePizza() {
		Pizza pizzaToUpdate = new Pizza();
		pizzaToUpdate.setId(1000);
		assertThat(pizzaService.updatePizza(pizzaToUpdate)).isNull();
		pizzaToUpdate = new Pizza();
		pizzaToUpdate.setId(2);
		pizzaToUpdate.setName("modified");
		assertThat(pizzaService.updatePizza(pizzaToUpdate)).isNotNull();
	}
}
