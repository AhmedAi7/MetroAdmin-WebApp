package com.javacode.Model;

import javax.persistence.*;

@Entity
@Table(name = "basic_ticket")
public class BasicTicket
{
    private Integer id;
    private Integer price;
    private Integer maximum_trips;

    public BasicTicket(){}

    public BasicTicket(Integer id, Integer price, Integer maximum_trips) {
        this.id = id;
        this.price = price;
        this.maximum_trips = maximum_trips;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Integer getMaximum_trips() {
        return maximum_trips;
    }

    public void setMaximum_trips(Integer maximum_trips) {
        this.maximum_trips = maximum_trips;
    }
}
