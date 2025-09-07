//package net.javaguide.Food_Ordering_Application.service.impl;
//
//import net.javaguide.Food_Ordering_Application.dto.UserRegistrationDto;
//import net.javaguide.Food_Ordering_Application.entity.Role;
//import net.javaguide.Food_Ordering_Application.entity.User;
//import net.javaguide.Food_Ordering_Application.repository.UserRepository;
//import net.javaguide.Food_Ordering_Application.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private final UserRepository userRepository;
//
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public User save(UserRegistrationDto userRegistrationDto) {
//
//        Collection<Role> roles = Collections.singleton(new Role("ROLE_USER"));
//
//        User user = new User(userRegistrationDto.getFirstName(),
//                   userRegistrationDto.getLastName(),
//                   userRegistrationDto.getEmail(),
//                   userRegistrationDto.getPassword(),
//                   roles
//                   );
//
//        return userRepository.save(user);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
//}
