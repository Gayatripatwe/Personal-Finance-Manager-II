package com.example.Personal.Finance.Management.entity;

import com.example.Personal.Finance.Management.Enum.Category;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column
    private double amount ;
    @ManyToOne()                               // Assuming 'user' is a reference to the User entity
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name="income_id")
    private Income income;
    @OneToMany(mappedBy = "budget")
    private List<Expenses> expenses;

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

    public Budget(Long id, double amount, User user, Category category,Income income) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.category = category;
        this.income=income;
    }
    public Budget() {





    }

}
