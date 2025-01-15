package net.javaguide.Food_Ordering_Application.mapper;

import net.javaguide.Food_Ordering_Application.dto.RestaurantDto;
import net.javaguide.Food_Ordering_Application.entity.Restaurant;

//To create the connection between RestaurantDto and Restaurant entity
public class RestaurantMapper {
                //return type  //method name     //parameter
    public static RestaurantDto mapToRestaurantDto(Restaurant restaurant){
        return new RestaurantDto(
                restaurant.getRestaurantId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getLocation(),
                restaurant.getContact_number(),
                restaurant.getOpen_hours(),
                restaurant.getStatus()

        );
    }

    public static Restaurant mapToRestaurant(RestaurantDto restaurantDto){
        return new Restaurant(
                restaurantDto.getRestaurantId(),
                restaurantDto.getName(),
                restaurantDto.getDescription(),
                restaurantDto.getLocation(),
                restaurantDto.getContact_number(),
                restaurantDto.getOpen_hours(),
                restaurantDto.getStatus()
        );
    }
}
