package org.example;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity()
@Table(name = "cars")
public class CarEntity extends PanacheEntity {
    public long id;

    @Column(name = "type")
    public String type;
    @Column(name = "model")
    public String model;
    @Column(name = "color")
    public String color;
    @Column(name = "maxSpeed")
    public int maxSpeed;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", Type='" + type + '\'' +
                ", Model=" + model +
                ", Color='" + color + '\'' +
                ", Max Speed=" + maxSpeed +
                '}';
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
