package net.javaguide.Food_Ordering_Application.service.impl;

import net.javaguide.Food_Ordering_Application.dto.MenuDto;
import net.javaguide.Food_Ordering_Application.entity.Menu;
import net.javaguide.Food_Ordering_Application.entity.Restaurant;
import net.javaguide.Food_Ordering_Application.exception.ResourceNotFoundException;
import net.javaguide.Food_Ordering_Application.mapper.MenuMapper;
import net.javaguide.Food_Ordering_Application.repository.MenuRepository;
import net.javaguide.Food_Ordering_Application.repository.RestaurantRepository;
import net.javaguide.Food_Ordering_Application.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {


    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public MenuDto createMenu(Long restaurantId,MenuDto menuDto) {

        Restaurant restaurant =  restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Restaurant is not exists in given Id :" + restaurantId)
                );



        Menu menu =  MenuMapper.mapToMenu(menuDto);
        menu.setRestaurantId(restaurant);
        Menu savedMenu = menuRepository.save(menu);
        return MenuMapper.mapToMenuDto(savedMenu);
    }

    @Override
    public MenuDto getMenuById(Long menuId) {

        Menu menu =  menuRepository.findById(menuId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Menu is not exist in given id :"+ menuId)
                );


        return MenuMapper.mapToMenuDto(menu);
    }

    @Override
    public List<MenuDto> getAllMenus() {

     List<Menu> menus = menuRepository.findAll();

     return menus.stream().map((menu) -> MenuMapper.mapToMenuDto(menu)).
             collect(Collectors.toList());
    }

    @Override
    public List<MenuDto> getMenus(Long restaurantId) {

       List<Menu> menu =  menuRepository.findAllById(Collections.singleton(restaurantId));

       if (menu.isEmpty()){
           throw new ResourceNotFoundException("No menus found for restaurant ID:" + restaurantId);
       }
        return menu.stream().map((menu1) -> MenuMapper.mapToMenuDto(menu1)).
                collect(Collectors.toList());
    }


}
