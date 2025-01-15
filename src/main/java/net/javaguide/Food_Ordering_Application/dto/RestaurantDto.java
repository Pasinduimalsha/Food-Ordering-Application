package net.javaguide.Food_Ordering_Application.dto;


public class RestaurantDto {
    private Long restaurantId;
    private String name;
    private String description;
    private String location;
    private String contact_number;
    private String open_hours;
    private String status;


    public RestaurantDto(Long restaurantId, String name, String description, String location, String contact_number, String open_hours, String status) {
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
        return "{" +
                "restaurantId :" + restaurantId +
                ", name :'" + name + '\'' +
                ", description :" + description + '\'' +
                ", location :" + location + '\'' +
                ", contact_number :" + contact_number + '\'' +
                ", open_hours :" + open_hours + '\'' +
                ", status :" + status + '\'' +
                '}';
    }
}
