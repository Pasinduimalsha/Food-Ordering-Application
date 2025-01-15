package net.javaguide.Food_Ordering_Application.repository;

import net.javaguide.Food_Ordering_Application.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = "SELECT * FROM menu WHERE restaurant_id = :restaurantId", nativeQuery = true)
    List<Menu> findByRestaurant_Id(@Param("restaurantId") Long restaurantId);
}
