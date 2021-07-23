package com.javacode.Model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="subscription")
public class Subscription {
    private Integer subscription_id;
    private Integer trips_num;
    private Integer region_num;
    private Integer months_num;
    private Integer price;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(Integer subscription_id) {
        this.subscription_id = subscription_id;
    }

    public Subscription()
    {
    }

    public Subscription(Integer subscription_id, Integer trips_num, Integer region_num, Integer months_num, Integer price) {
        this.subscription_id=subscription_id;
        this.trips_num = trips_num;
        this.region_num = region_num;
        this.months_num = months_num;
        this.price = price;
    }

    public int gettrips_num() {
        return trips_num;
    }

    public void settrips_num(int trips_num) {
        this.trips_num = trips_num;
    }

    public int getregion_num() {
        return region_num;
    }

    public void setregion_num(int region_num) {
        this.region_num = region_num;
    }

    public int getmonths_num() {
        return months_num;
    }

    public void setmonths_num(int months_num) {
        this.months_num = months_num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
