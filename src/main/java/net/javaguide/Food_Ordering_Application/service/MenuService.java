package net.javaguide.Food_Ordering_Application.service;

import net.javaguide.Food_Ordering_Application.dto.MenuDto;
import net.javaguide.Food_Ordering_Application.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    MenuDto createMenu(Long restaurantId,MenuDto menuDto);

    MenuDto getMenuById(Long menuId);

    List<MenuDto> getAllMenus();

    List<MenuDto> getMenus(Long restaurantId);
}