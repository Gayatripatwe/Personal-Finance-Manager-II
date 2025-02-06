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
    @ManyToOne
    @JoinColumn(name="income_id")
    private Income income;

    public Long getId() {
        return id;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
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

    public SavingGoals(Long id, User user, String goalName, Double targetAmount, Double currentamount, LocalDate deadline,Income income) {
        this.id = id;
        this.user = user;
        this.GoalName = goalName;
       this.TargetAmount = targetAmount;
        this.Currentamount = currentamount;
        this.deadline = deadline;
        this.income=income;
    }

    public SavingGoals() {

    }
}
