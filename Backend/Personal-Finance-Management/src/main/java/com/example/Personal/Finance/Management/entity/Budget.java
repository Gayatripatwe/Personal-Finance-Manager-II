
package com.example.Personal.Finance.Management.entity;

import com.example.Personal.Finance.Management.Enum.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "income_id", nullable = false)
    @JsonBackReference
    private Income income;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Expenses> expenses;

    // Default constructor
    public Budget() {
    }

    // Parameterized constructor
    public Budget(Long id, double amount, User user, Category category, Income income) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.category = category;
        this.income = income;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expenses> expenses) {
        this.expenses = expenses;
    }


    public void setUserId(Long userId) {
    }

    public void setIncomeId(Long incomeId) {
    }

    public Long getUserId() {
        return user.getUserid();
    }

    public Long getIncomeId() {
        return income.getId();
    }


}
