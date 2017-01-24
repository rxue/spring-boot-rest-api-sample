package org.apche.restaurant.jparepository;

import java.util.List;

import org.apche.restaurant.model.Pizza;
import org.apche.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PizzaJpaRepository extends JpaRepository<Pizza, Integer> {
	
	public List<Pizza> findByRestaurant(Restaurant restaurant);
	
	public Pizza findByRestaurantAndName(Restaurant restaurant, String name);
	public List<Pizza> findByNameLikeIgnoreCase(String name);

}
