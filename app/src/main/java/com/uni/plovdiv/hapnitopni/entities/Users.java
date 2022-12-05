package com.uni.plovdiv.hapnitopni.entities;

public class Users {

    private int id;
    private String email;
    private String password;
    private String name;



    //login purpose
    public Users(String email, String password) {
        this.email  = email;
        this.password = password;
    }


    //registration purpose
    public Users( String password,String email, String name) {
        this.email  = email;
        this.password = password;
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
