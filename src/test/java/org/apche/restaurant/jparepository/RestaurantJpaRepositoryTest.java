package org.apche.restaurant.jparepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.apche.restaurant.AbstractTest;
import org.apche.restaurant.model.Restaurant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RestaurantJpaRepositoryTest extends AbstractTest {
	@Autowired
	private RestaurantJpaRepository restaurantJpaRepository;
	@Test
	public void TestfindAllRestaurantsWithoutPizzaMenu() {
		List<Restaurant> restaurants = restaurantJpaRepository.findAllRestaurantsWithoutPizzaMenu();
		assertEquals(3, restaurants.size());
		restaurants.stream().forEach(e -> {
			assertThat(e.getName()).isNotNull();
			assertThat(e.getPizza_menu()).isNull();
		});
	}
}
