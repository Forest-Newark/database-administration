package com.forestnewark.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by forestnewark on 4/24/17.
 */
@Entity
public class Musician {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Musician(){}

    public Musician(String name) {
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
