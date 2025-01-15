package net.javaguide.Food_Ordering_Application.mapper;

import net.javaguide.Food_Ordering_Application.dto.MenuDto;
import net.javaguide.Food_Ordering_Application.entity.Menu;

public class MenuMapper {
    public static MenuDto mapToMenuDto(Menu menu){
        return new MenuDto(
                menu.getMenuId(),
                menu.getName(),
//                menu.getRestaurantId(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getCategory(),
                menu.getStockQuantity(),
                menu.getAvailability()

        );
    }

    public static Menu mapToMenu(MenuDto menuDto){


        return new Menu(
                menuDto.getMenuId(),
                menuDto.getName(),
//                menuDto.getRestaurantId(),
                menuDto.getDescription(),
                menuDto.getPrice(),
                menuDto.getCategory(),
                menuDto.getStockQuantity(),
                menuDto.getAvailability()
        );
    }
}
