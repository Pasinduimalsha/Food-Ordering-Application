package net.javaguide.Food_Ordering_Application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Location")
    private String location;
    @Column(name = "Contact Number")
    private String contact_number;
    @Column(name = "Open Hours")
    private String open_hours;
    @Column(name = "Status")
    private String status;

    public Restaurant() {

    }

    public Restaurant(Long restaurantId, String name, String description, String location, String contact_number, String open_hours, String status) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.contact_number = contact_number;
        this.open_hours = open_hours;
        this.status = status;

    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getOpen_hours() {
        return open_hours;
    }

    public void setOpen_hours(String open_hours) {
        this.open_hours = open_hours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", open_hours='" + open_hours + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
