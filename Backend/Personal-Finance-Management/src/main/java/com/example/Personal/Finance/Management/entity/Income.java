package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column
    private Double amount;
    @Column
    private String discription;
    @Column
    private LocalDate date;
    @ManyToOne()                            // Assuming 'user' is a reference to the User entity
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "income")
    private List<Budget> budgets;
    @OneToMany
    private List<SavingGoals> SavingGoals;


    public List<SavingGoals> getSavingGoals() {
        return SavingGoals;
    }

    public void setSavingGoals(List<SavingGoals> savingGoals) {
        SavingGoals = savingGoals;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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

    public Income(Long id, Double amount, String discription, LocalDate date, User user) {
        this.id = id;
        this.amount = amount;
        this.discription = discription;
        this.date = date;
        this.user = user;
    }

    public Income() {

    }

}
