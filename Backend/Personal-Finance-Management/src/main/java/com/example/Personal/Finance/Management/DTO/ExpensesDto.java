package com.example.Personal.Finance.Management.DTO;

import com.example.Personal.Finance.Management.Enum.Category;
import com.example.Personal.Finance.Management.entity.Budget;

import java.time.LocalDate;
import java.util.LongSummaryStatistics;

public class ExpensesDto {
    private double amount;
    private Long user_id;
    private String description;
    private Category category;
    private Long budget_id;
    private LocalDate date;  // **Add this line**
    public Long getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(Long budget_id) {
        this.budget_id = budget_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ExpensesDto(double amount, Long user_id, String description, Category category, Long budget_id, LocalDate date) {
        this.amount = amount;
        this.user_id = user_id;
        this.description = description;
        this.category = category;
        this.budget_id = budget_id;
        this.date = date;
    }


    public ExpensesDto() {
    }
}
