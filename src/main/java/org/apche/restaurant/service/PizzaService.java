package org.apche.restaurant.service;

import java.util.List;

import org.apche.restaurant.jparepository.PizzaJpaRepository;
import org.apche.restaurant.jparepository.RestaurantJpaRepository;
import org.apche.restaurant.model.Pizza;
import org.apche.restaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PizzaService {
	@Autowired
	private PizzaJpaRepository pizzaRepository;
	@Autowired
	private RestaurantJpaRepository restaurantRepository;
	
	public List<Pizza> getAllPizzas() {
		return this.pizzaRepository.findAll();
	}
	
	public List<Pizza> getAllPizzasByRestaurantId(int restaurantId) {
		Restaurant restaurant = this.restaurantRepository.findOne(restaurantId);
		return this.pizzaRepository.findByRestaurant(restaurant);
	}
	public List<Pizza> getPizzasByName(String name) {
		return this.pizzaRepository.findByNameLikeIgnoreCase("%" + name + "%");
	}
	public Pizza getPizzaById(int pizzaId) {
		return this.pizzaRepository.findOne(pizzaId);
	}
	//ref: http://stackoverflow.com/questions/33281369/how-to-create-a-model-in-spring-from-json-where-the-foreign-key-is-referenced
	public Pizza createPizza(Pizza pizza) {
		Restaurant parentRestaurant = restaurantRepository.findOne(pizza.getRestaurant_id()); 
		if (parentRestaurant == null) 
			return null;
		else {
			pizza.setRestaurant(parentRestaurant);
			return this.pizzaRepository.save(pizza);
		}
	}
	
	public Pizza updatePizza(Pizza pizza) {
		if (this.pizzaRepository.findOne(pizza.getId()) == null)
			return null;
		return this.pizzaRepository.save(pizza);
	}
	public void deletePizza(int pizzaId) {
		this.pizzaRepository.delete(pizzaId);
	}
	
}

