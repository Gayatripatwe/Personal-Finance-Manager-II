package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "savings_goal")
public class SavingGoals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String goalName;

    @Column(nullable = false)
    private Double targetAmount;

    @Column(nullable = false)
    private Double currentAmount = 0.0; // Default 0.0

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private Double income; // User's income limit

    public SavingGoals() {}

    public SavingGoals(Long id, User user, String goalName, Double targetAmount, Double currentAmount, LocalDate deadline, Double income) {
        this.id = id;
        this.user = user;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline = deadline;
        this.income = income;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getGoalName() { return goalName; }
    public void setGoalName(String goalName) { this.goalName = goalName; }

    public Double getTargetAmount() { return targetAmount; }
    public void setTargetAmount(Double targetAmount) { this.targetAmount = targetAmount; }

    public Double getCurrentAmount() { return currentAmount; }
    public void setCurrentAmount(Double currentAmount) { this.currentAmount = currentAmount; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public Double getIncome() { return income; }
    public void setIncome(Double income) { this.income = income; }
}
