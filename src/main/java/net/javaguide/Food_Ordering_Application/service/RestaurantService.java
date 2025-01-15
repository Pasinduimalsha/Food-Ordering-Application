package net.javaguide.Food_Ordering_Application.service;

import net.javaguide.Food_Ordering_Application.dto.RestaurantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
  //return type
  RestaurantDto createRestaurant(RestaurantDto restaurantDto);

  RestaurantDto getRestaurantById(Long restaurantId);

  List<RestaurantDto> getAllRestaurants();

  RestaurantDto updateRestaurant(Long restaurantId, RestaurantDto updatedRestaurant);

  void deleteRestaurant(Long restaurantId);
}
