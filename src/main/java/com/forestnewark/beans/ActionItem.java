package com.forestnewark.beans;

/**
 * Created by forestnewark on 4/18/17.
 */
public class ActionItem {

    private Integer id;
    private String item;
    private String status;
    private String addedBy;

    public ActionItem(){}

    public ActionItem(Integer id, String item, String status, String addedBy) {
        this.id = id;
        this.item = item;
        this.status = status;
        this.addedBy = addedBy;
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

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}
