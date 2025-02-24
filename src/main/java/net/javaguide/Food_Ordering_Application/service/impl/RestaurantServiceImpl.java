package net.javaguide.Food_Ordering_Application.service.impl;

import lombok.AllArgsConstructor;
import net.javaguide.Food_Ordering_Application.dto.RestaurantDto;
import net.javaguide.Food_Ordering_Application.entity.Restaurant;
import net.javaguide.Food_Ordering_Application.exception.GlobalExceptionHandler;
import net.javaguide.Food_Ordering_Application.exception.ResourceNotFoundException;
import net.javaguide.Food_Ordering_Application.mapper.RestaurantMapper;
import net.javaguide.Food_Ordering_Application.repository.RestaurantRepository;
import net.javaguide.Food_Ordering_Application.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {

        Restaurant restaurant = RestaurantMapper.mapToRestaurant(restaurantDto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.mapToRestaurantDto(savedRestaurant) ;
    }

    @Override
    public RestaurantDto getRestaurantById(Long restaurantId) {

      Restaurant restaurant =  restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> {
                    System.out.println("Restaurant not found for ID: " + restaurantId);

                   return new ResourceNotFoundException("Restaurant is not exist with given Id"+restaurantId);
                });

        return RestaurantMapper.mapToRestaurantDto(restaurant);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map((restaurant) -> RestaurantMapper.mapToRestaurantDto(restaurant))
                .collect(Collectors.toList());

    }

    @Override
    public RestaurantDto updateRestaurant(Long restaurantId, RestaurantDto updatedRestaurant) {
      Restaurant restaurant = restaurantRepository.findById(restaurantId)
               .orElseThrow(
                       () -> new ResourceNotFoundException("Restaurant is not exist with given Id : "+ restaurantId)
               );
      restaurant.setName(updatedRestaurant.getName());
      restaurant.setDescription(updatedRestaurant.getDescription());
      restaurant.setLocation(updatedRestaurant.getLocation());
      restaurant.setContact_number(updatedRestaurant.getContact_number());
      restaurant.setOpen_hours(updatedRestaurant.getOpen_hours());
      restaurant.setStatus(updatedRestaurant.getStatus());

      Restaurant restaurant1 = restaurantRepository.save(restaurant);

        return RestaurantMapper.mapToRestaurantDto(restaurant1) ;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {

    Restaurant restaurant =    restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Restaurant is not exists in given Id : "+ restaurantId)
                );
    restaurantRepository.deleteById(restaurantId);
    }
}
