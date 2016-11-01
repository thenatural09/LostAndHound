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
    public String name;

    @Column(nullable = false)
    public String type;

    @Column(nullable = false)
    public String color;

    @Column(nullable = false)
    public String image;

    @Transient
    public boolean isMe;

    @ManyToOne
    public User user;

    public Dog(String name, String type, String color, String image, User user) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.image = image;
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

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
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
