//package com.example.Personal.Finance.Management.entity;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Table(name="Incomes")
//public class Income {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id ;
//    @Column
//    private Double amount;
//    @Column
//    private String description;
//    @Column
//    private LocalDate date;
//    @ManyToOne()
//    @JoinColumn(name = "user_id")
//    private User user;
//    @OneToMany(mappedBy = "income",cascade = CascadeType.ALL)
//    private List<Budget> budgets;
//    @OneToMany(mappedBy = "income",cascade = CascadeType.ALL)
//    private List<SavingGoals> savingGoals;
//
//
//    public List<SavingGoals> getSavingGoals() {
//        return savingGoals;
//    }
//
//    public void setSavingGoals(List<SavingGoals> savingGoals) {
//        this.savingGoals = savingGoals;
//    }
//
//    public List<Budget> getBudgets() {
//        return budgets;
//    }
//
//    public void setBudgets(List<Budget> budgets) {
//        this.budgets = budgets;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Income(Long id, Double amount, String discription, LocalDate date, User user) {
//        this.id = id;
//        this.amount = amount;
//        this.description = discription;
//        this.date = date;
//        this.user = user;
//    }
//
//    public Income() {
//
//    }
//
//}




package com.example.Personal.Finance.Management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "income", cascade = CascadeType.ALL)
    private List<Budget> budgets;

    @OneToMany(mappedBy = "income", cascade = CascadeType.ALL)
    private List<SavingGoals> savingGoals;

    // Getters and Setters
    public List<SavingGoals> getSavingGoals() {
        return savingGoals;
    }

    public void setSavingGoals(List<SavingGoals> savingGoals) {
        this.savingGoals = savingGoals;
        if (savingGoals != null) {
            for (SavingGoals goal : savingGoals) {
                goal.setIncome(this);
            }
        }
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

    // Constructor with parameters
    public Income(Long id, Double amount, String description, LocalDate date, User user) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    // Default constructor
    public Income() {
    }
}
