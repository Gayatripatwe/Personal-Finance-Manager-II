package com.example.Personal.Finance.Management.DTO;

import com.example.Personal.Finance.Management.Enum.Category;

public class BudgetDto {
    private double amount;
    private Long user_id;
    private Long income_id;
    private Category category;

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

    public Long getIncome_id() {
        return income_id;
    }

    public void setIncome_id(Long income_id) {
        this.income_id = income_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public BudgetDto(double amount, Long user_id, Long income_id, Category category) {
        this.amount = amount;
        this.user_id = user_id;
        this.income_id = income_id;
        this.category = category;
    }

    public BudgetDto() {

    }

}
