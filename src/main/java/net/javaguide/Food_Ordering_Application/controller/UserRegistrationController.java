//package net.javaguide.Food_Ordering_Application.controller;
//
//import net.javaguide.Food_Ordering_Application.dto.UserRegistrationDto;
//import net.javaguide.Food_Ordering_Application.entity.User;
//import net.javaguide.Food_Ordering_Application.service.UserService;
//import net.javaguide.Food_Ordering_Application.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/registration")
//public class UserRegistrationController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserRegistrationController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<User> registerUserAccount(@RequestBody UserRegistrationDto userRegistrationDto){
//
//        User savedUser = userService.save(userRegistrationDto);
//       return ResponseEntity.ok(savedUser);
//
//    }
//}
