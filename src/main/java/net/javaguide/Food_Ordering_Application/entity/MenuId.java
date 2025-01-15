package net.javaguide.Food_Ordering_Application.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MenuId implements Serializable {

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    public MenuId() {
    }

    public MenuId(Long menuId, Long restaurantId) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuId menuId1)) return false;
        return Objects.equals(getMenuId(), menuId1.getMenuId()) &&
                Objects.equals(getRestaurantId(), menuId1.getRestaurantId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenuId(), getRestaurantId());
    }

    @Override
    public String toString() {
        return "MenuId{" +
                "menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}

