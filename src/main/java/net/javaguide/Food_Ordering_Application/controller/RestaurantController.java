package net.javaguide.Food_Ordering_Application.controller;

import lombok.AllArgsConstructor;
import net.javaguide.Food_Ordering_Application.dto.RestaurantDto;
import net.javaguide.Food_Ordering_Application.entity.Restaurant;
import net.javaguide.Food_Ordering_Application.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/restaurant")

public class RestaurantController {

    @Autowired
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Build create restaurant rest api
    @PostMapping("/")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto){
        System.out.println("Inside the api----------");
//        System.out.println("Object value:"+ restaurantDto.getRestaurantId());
        RestaurantDto savedRestaurant =  restaurantService.createRestaurant(restaurantDto);
        System.out.println("Inside the api----------");
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED) ;
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable("id") Long RestaurantId){
        System.out.println("Id is :" + RestaurantId);

       RestaurantDto getRestaurant = restaurantService.getRestaurantById(RestaurantId);

       return ResponseEntity.ok(getRestaurant);
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
       List<RestaurantDto> getAllRestaurants =  restaurantService.getAllRestaurants();

        return ResponseEntity.ok(getAllRestaurants);
    }

    @PutMapping("{id}")
    public  ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable("id") Long RestaurantId,
                                                        @RequestBody RestaurantDto updatedRestaurant){
      RestaurantDto updateRestaurant =   restaurantService.updateRestaurant(RestaurantId, updatedRestaurant);

      return ResponseEntity.ok(updateRestaurant);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") Long restaurantId){
       restaurantService.deleteRestaurant(restaurantId);

       return ResponseEntity.ok("Employee Deleted successfully");
    }

}
