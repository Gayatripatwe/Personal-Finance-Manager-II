package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.entity.Budget;
import org.springframework.http.ResponseEntity;

public interface budgetService {
    ResponseEntity<Budget> addBudget(Budget newBudget);
    ResponseEntity<Budget> updateBudget(Long id,Budget updatedbudget);
    ResponseEntity<Budget> getBudgetById(Long id);
    ResponseEntity<Void> deleteBudget(Long id);
}
