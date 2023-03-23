package org.example;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity()
@Table(name = "cars")
public class CarEntity extends PanacheEntity {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    public long id;

    @Column(name = "type")
    public String type;
    @Column(name = "model")
    public String model;
    @Column(name = "color")
    public String color;
    @Column(name = "maxSpeed")
    public int maxSpeed;

    public static List<CarEntity> findByQuery(String query) {
        return CarEntity.listAll().stream().
                map(p -> (CarEntity) p)
                .filter(flower ->
                        query.contains(flower.type) ||
                                query.contains(flower.model))
                .collect(Collectors.toList());
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
