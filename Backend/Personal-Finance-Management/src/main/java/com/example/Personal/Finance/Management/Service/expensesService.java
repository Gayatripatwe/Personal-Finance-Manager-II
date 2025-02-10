package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.DTO.ExpensesDto;
import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface expensesService {
     ExpensesDto addExpenses(ExpensesDto expenseDto);
     ExpensesDto getExpenseById(Long id);
     List<ExpensesDto> getExpensesByUserId(Long userId);
     void deleteExpenseById(Long id);
     ResponseEntity<ExpensesDto> updateExpense(Long id, ExpensesDto updatedExpenseDto);

}
