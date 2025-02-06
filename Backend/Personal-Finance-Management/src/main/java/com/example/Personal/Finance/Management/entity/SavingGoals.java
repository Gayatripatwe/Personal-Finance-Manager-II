package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="SavingsGoal")
public class SavingGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String GoalName;
    @Column
    private Double TargetAmount;
    @Column
    private Double currentAmount;
    @Column
    private LocalDate deadline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGoalName() {
        return GoalName;
    }

    public void setGoalName(String goalName) {
        GoalName = goalName;
    }

    public Double getTargetAmount() {
        return TargetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        TargetAmount = targetAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public SavingGoals(Long id, User user, String goalName, Double targetAmount, Double currentAmount, LocalDate deadline) {
        this.id = id;
        this.user = user;
        GoalName = goalName;
        TargetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline = deadline;
    }

    public SavingGoals() {

    }

}
