package net.javaguide.Food_Ordering_Application.dto;

import net.javaguide.Food_Ordering_Application.entity.Restaurant;

public class MenuDto {

    private Long menuId;
    private Restaurant restaurantId;
    private String name;
    private String description;
    private String price;
    private String category;
    private int stockQuantity;
    private String availability;

    public MenuDto() {
    }

    public MenuDto(Long menuId, String name, String description, String price, String category, int stockQuantity, String availability) {
        this.menuId = menuId;
        this.name = name;
//        this.restaurantId = restaurantId;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.availability = availability;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", availability='" + availability + '\'' +
                '}';
    }
}


