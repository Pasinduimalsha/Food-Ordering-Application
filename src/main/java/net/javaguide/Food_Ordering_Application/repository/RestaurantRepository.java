package net.javaguide.Food_Ordering_Application.repository;

import net.javaguide.Food_Ordering_Application.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

}
