package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long user_id;
@Column
private String name;
@Column
    private String email  ;
@Column
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserid() {
        return user_id;
    }

    public void setUserid(long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(long user_id,String  name , String email, String password) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.name= name;
    }

    public User() {

    }

}
