package com.forestnewark.beans;



/**
 * Created by forestnewark on 4/18/17.
 */


public class User{


    private Integer id;


    private String firstName;


    private String lastName;


    private String rank;


    private String permission;

    private String email;

    private String username;

    private String password;

    public User(){

    }

    public User(Integer id,String firstName, String lastName, String rank, String permission,String email,String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.permission = permission;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
