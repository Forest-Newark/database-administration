package com.forestnewark.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Forest Newark on 4/18/17.
 */

@Entity
@Table(name = "actionitem")
public class ActionItem {

    @Id
    @GeneratedValue
    private Integer id;

    private String item;
    private String status;
    private String priority;
    private String comments;


    public ActionItem(){}

    public ActionItem(String item, String status,String priority, String comments) {

        this.item = item;
        this.status = status;
        this.priority = priority;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
