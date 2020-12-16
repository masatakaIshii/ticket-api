package fr.esgi.ticketapi.core.entity;

public class Order {
    private Integer id;
    private Integer price;
    private Integer userId;

    public Order(Integer id, Integer price, Integer userId) {
        this.id = id;
        this.price = price;
        this.userId = userId;
    }
}
