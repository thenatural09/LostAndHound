package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by Troy on 10/31/16.
 */
@Entity
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String type;

    @Column(nullable = false)
    String color;

    @Column(nullable = false)
    String image;

    @Column(nullable = false)
    boolean isFound;

    @ManyToOne
    User user;

    public Dog(String name, String type, String color, String image, boolean isFound, User user) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.image = image;
        this.isFound = isFound;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dog() {

    }
}
