package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.DTO.BudgetDto;
import com.example.Personal.Finance.Management.entity.Budget;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface budgetService {
    Budget addBudget(BudgetDto budgetDto);
    Budget updateBudget(Long id, BudgetDto updatedBudgetDto);
    Budget getBudgetById(Long id);
    ResponseEntity<Void> deleteBudget(Long id);
//    List<Budget> getBudgetsByUserId(Long user_id);

}
