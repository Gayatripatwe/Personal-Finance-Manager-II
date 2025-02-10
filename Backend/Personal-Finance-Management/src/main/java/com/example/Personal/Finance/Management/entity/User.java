package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
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
        return userId;
    }

    public void setUserid(long userId) {
        this.userId = userId;
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

    public User(long user_id,String  Name , String email, String password) {
        this.userId = user_id;
        this.email = email;
        this.password = password;
        this.name= Name;
    }

    public User() {

    }

}
