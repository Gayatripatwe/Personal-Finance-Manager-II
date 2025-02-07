package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column
    private Double amount;
    @Column
    private String description;
    @Column
    private LocalDate date;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Income(Long id, Double amount, String description, LocalDate date, User user) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public Income() {

    }

}
