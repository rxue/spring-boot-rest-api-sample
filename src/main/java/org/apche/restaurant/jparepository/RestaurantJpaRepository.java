package org.apche.restaurant.jparepository;

import java.util.List;

import org.apche.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Integer> {
	
	//NB such JPA query's table name is case sensitive and is the same as the corresponding model name by default
	@Query("select new Restaurant(a.id, a.name, a.city, a.address, a.email, a.phone, a.open_at, a.close_at) from Restaurant a")
	public List<Restaurant> findAllRestaurantsWithoutPizzaMenu();
	
	//public Restaurant findByName();

}
