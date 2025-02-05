package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
@Id
@GeneratedValue
private long user_id;
@Column
private String Name;
@Column
    private String email  ;
@Column
    private String password;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getUse_id() {
        return user_id;
    }

    public void setUse_id(long user_id) {
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

    public User(long user_id,String  Name , String email, String password) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.Name= Name;
    }

    public User() {

    }

}
