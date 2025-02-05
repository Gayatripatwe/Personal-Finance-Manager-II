package com.example.Personal.Finance.Management.entity;

import com.example.Personal.Finance.Management.Enum.Category;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="Expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column
    private String discription;
    @Column
    private Double amount;
    @ManyToOne()                               // Assuming 'user' is a reference to the User entity
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private LocalDate date ;
@Column
    @Enumerated(EnumType.STRING)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Expenses(Long id, String discription, Double amount, User user, LocalDate date, Category category) {
        this.id = id;
        this.discription = discription;
        this.amount = amount;
        this.user = user;
        this.date = date;
        this.category = category;
    }
    public Expenses() {

    }


}
