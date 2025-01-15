package net.javaguide.Food_Ordering_Application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
//@IdClass(MenuId.class)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_Id")
    private Long menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_Id", nullable = false)
    private Restaurant restaurantId;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private String price;

    @Column(name = "Category")
    private String category;

    @Column(name = "Stock Quantity")
    private int stockQuantity;

    @Column(name = "Availability")
    private String availability;

    public Menu() {
    }

    public Menu(Long menuId, String name, String description, String price, String category, int stockQuantity, String availability) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Menu{" +
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

