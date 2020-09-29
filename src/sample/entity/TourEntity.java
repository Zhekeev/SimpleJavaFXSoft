package sample.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigInteger;
import java.util.Objects;

public class TourEntity {
    private Integer id;
    private String name;
    private Integer price;
    private Integer duration;

    public TourEntity(){

    }

    public TourEntity(String name, Integer price, Integer duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public TourEntity(int id, String name, int price, int duration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
