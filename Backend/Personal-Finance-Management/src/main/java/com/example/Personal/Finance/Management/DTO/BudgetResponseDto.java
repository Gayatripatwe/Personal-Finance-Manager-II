package com.example.Personal.Finance.Management.DTO;

import com.example.Personal.Finance.Management.Enum.Category;

public class BudgetResponseDto {
    private Long id;
    private double amount;
    private Category category;
    private Long user_id;


    public double getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public BudgetResponseDto(Long id, double amount, Category category, Long user_id) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.user_id = user_id;
    }

    public BudgetResponseDto() {

    }

}
