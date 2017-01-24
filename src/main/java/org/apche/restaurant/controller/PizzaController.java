package org.apche.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.apche.restaurant.model.Pizza;
import org.apche.restaurant.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
//@RequestMapping("/rest/pizza")
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	@RequestMapping("/rest/pizzas")
	public ResponseEntity<List<Pizza>> getAllPizzas(final WebRequest request) {
		Map<String,String[]> parameterMap = request.getParameterMap();
		List<Pizza> foundPizzas = this.pizzaService.getAllPizzas();
		String[] restaurantIdStrs = parameterMap.get("restaurant_id");
		String[] pizzaNames = parameterMap.get("name");
		if (restaurantIdStrs != null) {
			Integer restaurantId = Integer.parseInt(restaurantIdStrs[0]);
			foundPizzas = this.pizzaService.getAllPizzasByRestaurantId(restaurantId);
		}
		else if (pizzaNames != null) foundPizzas = this.pizzaService.getPizzasByName(pizzaNames[0]);
		//
		if (foundPizzas.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foundPizzas, HttpStatus.FOUND);
	}
	@RequestMapping("/rest/pizza/{id}")
	public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") Integer pizzaId) {
		Pizza pizza = this.pizzaService.getPizzaById(pizzaId);
		if (pizza == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(pizza, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/rest/pizza", method = RequestMethod.POST)
	public ResponseEntity<Pizza> createRestaurant(@RequestBody Pizza pizza) {
		Pizza createdPizza = this.pizzaService.createPizza(pizza);
		if (createdPizza == null)
			return new ResponseEntity<>(createdPizza, HttpStatus.BAD_REQUEST);
		else 
			return new ResponseEntity<>(createdPizza, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/rest/pizza/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pizza> updateRestaurant(@PathVariable("id") int id, @RequestBody Pizza pizza) {
		pizza.setId(id);
		Pizza updatedPizza = this.pizzaService.updatePizza(pizza);
		if (updatedPizza == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else 
			return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value = "/rest/pizza/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Pizza> deletePizza(@PathVariable("id") Integer id) {
		this.pizzaService.deletePizza(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
