package com.example.Personal.Finance.Management.Controller;

import com.example.Personal.Finance.Management.DTO.ExpensesDto;
import com.example.Personal.Finance.Management.Service.budgetService;
import com.example.Personal.Finance.Management.Service.expensesService;
import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth/expenses")
public class expensesController {
    private final expensesService ExpenseService;
    private final budgetService BudgetService;

    public expensesController(expensesService expenseService, budgetService BudgetService) {
        this.ExpenseService = expenseService;
        this.BudgetService = BudgetService;
    }

    // Create Expense
    @PostMapping("/addexpenses")
    public ResponseEntity<?> addExpenses(@RequestBody ExpensesDto expenseDto) {
        System.out.println("Received Expense Data: " + expenseDto);
        try {
            ExpensesDto savedExpenseDto = ExpenseService.addExpenses(expenseDto);
            return ResponseEntity.ok(savedExpenseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Get Expense by ID
    @GetMapping("getexpensebyid/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try {
            ExpensesDto expenseDto = ExpenseService.getExpenseById(id);
            return ResponseEntity.ok(expenseDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get Expenses by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getExpensesByUserId(@PathVariable Long userId) {
        try {
            List<ExpensesDto> expenses = ExpenseService.getExpensesByUserId(userId);
            return ResponseEntity.ok(expenses);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //updatemethod
    @PutMapping("/expenses/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpensesDto updatedExpenseDto) {
        try {
            return ExpenseService.updateExpense(id, updatedExpenseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //    / Delete Expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable Long id) {
        try {
            ExpenseService.deleteExpenseById(id);
            return ResponseEntity.ok("Expense deleted successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}



