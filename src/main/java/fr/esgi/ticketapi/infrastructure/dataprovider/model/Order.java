package fr.esgi.ticketapi.infrastructure.dataprovider.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ticket")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer price;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public fr.esgi.ticketapi.core.entity.Order asOrderEntity() {
        return new fr.esgi.ticketapi.core.entity.Order(this.id, this.price, this.userId);
    }


}
