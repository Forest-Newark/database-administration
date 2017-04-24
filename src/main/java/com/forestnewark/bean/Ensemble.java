package com.forestnewark.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by forestnewark on 4/24/17.
 */
@Entity
public class Ensemble {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Ensemble(){}

    public Ensemble(String name) {
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
