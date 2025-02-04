package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="SavingsGoal")
public class SavingGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()                            // Assuming 'user' is a reference to the User entity
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String GoalName;
    @Column
    private Double TargetAmount;
    @Column
    private Double Currentamount;
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

    public Double getCurrentamount() {
        return Currentamount;
    }

    public void setCurrentamount(Double currentamount) {
        Currentamount = currentamount;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public SavingGoals(Long id, User user, String goalName, Double targetAmount, Double currentamount, LocalDate deadline) {
        this.id = id;
        this.user = user;
        GoalName = goalName;
        TargetAmount = targetAmount;
        Currentamount = currentamount;
        this.deadline = deadline;
    }

    public SavingGoals() {

    }
}
