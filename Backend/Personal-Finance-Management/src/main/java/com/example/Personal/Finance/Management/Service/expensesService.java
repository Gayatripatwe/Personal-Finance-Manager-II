package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface expensesService {
     ResponseEntity<Expenses> addExpenses(Expenses E);
     ResponseEntity<Expenses> updateExpenses(Long id,Expenses updatedExp);
     ResponseEntity<Void> deleteExpense(Long id);
     ResponseEntity<Expenses> getExpenseById(Long id);
}
