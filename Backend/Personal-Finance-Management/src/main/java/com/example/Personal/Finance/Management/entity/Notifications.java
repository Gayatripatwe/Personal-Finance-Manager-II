package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column
    private String MessageText;
    @ManyToOne()                               // Assuming 'user' is a reference to the User entity
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notifications(Long id, String messageText, User user) {
        this.id = id;
        MessageText = messageText;
        this.user = user;
    }
    public Notifications() {

    }


}
