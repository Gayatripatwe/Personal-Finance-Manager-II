package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
@Id
@GeneratedValue
private long use_id;

@Column
    private String email  ;
@Column
    private String password;

    public long getUse_id() {
        return use_id;
    }

    public void setUse_id(long use_id) {
        this.use_id = use_id;
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

    public User(long use_id, String email, String password) {
        this.use_id = use_id;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

}
