package com.forestnewark.bean;


import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * Created by forestnewark on 4/24/17.
 */
@Entity
public class Catagory {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Catagory(){}

    public Catagory(String name) {
        this.name = name;
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
}
