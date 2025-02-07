
package com.example.Personal.Finance.Management.entity;

import com.example.Personal.Finance.Management.Enum.Category;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable=false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;

    // Constructors
    public Expenses() {}

    public Expenses(Long id, String description, Double amount, User user, LocalDate date, Category category, Budget budget) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.user = user;
        this.date = date;
        this.category = category;
        this.budget = budget;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Budget getBudget() { return budget; }
    public void setBudget(Budget budget) { this.budget = budget; }
}
