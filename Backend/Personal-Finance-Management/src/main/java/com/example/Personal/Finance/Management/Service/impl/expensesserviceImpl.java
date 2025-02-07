
package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.DTO.ExpensesDto;
import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Repository.budgetRepository;
import com.example.Personal.Finance.Management.Repository.expensesRepository;
import com.example.Personal.Finance.Management.Service.expensesService;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Expenses;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class expensesserviceImpl implements expensesService {

    private final expensesRepository ExpensesRepository ;
    private  final UserRepository userRepository;
    private final budgetRepository BudgetRepository;

    public expensesserviceImpl(expensesRepository ExpensesRepository,UserRepository userRepository,budgetRepository BudgetRepository) {
        this.ExpensesRepository = ExpensesRepository;
        this.userRepository = userRepository;
        this.BudgetRepository  = BudgetRepository;
    }

    @Override
    public ExpensesDto addExpenses(ExpensesDto expenseDto) {
        // Find User
        User user = userRepository.findById(expenseDto.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        // Find Budget
        Budget budget = BudgetRepository.findById(expenseDto.getBudget_id())
                .orElseThrow(() -> new IllegalArgumentException("Budget not found."));

        // Check if Expense is within Budget
        if (expenseDto.getAmount() > budget.getAmount()) {
            throw new IllegalArgumentException("Expense cannot be more than the available budget.");
        }

        // Create Expenses Entity from DTO
        Expenses expense = new Expenses();
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(expenseDto.getCategory());
        expense.setUser(user);
        expense.setBudget(budget);

        // **Set date (default to current date if not provided)**
        if (expenseDto.getDate() == null) {
            expense.setDate(LocalDate.now());
        } else {
            expense.setDate(expenseDto.getDate());
        }

        // Save Expense
        Expenses savedExpense = ExpensesRepository.save(expense);

        return mapEntityToDto(savedExpense);
    }

    @Override
    public ExpensesDto getExpenseById(Long id) {
        Expenses expense = ExpensesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Expense not found."));
        return mapEntityToDto(expense);
    }

    @Override
    public List<ExpensesDto> getExpensesByUserId(Long userId) {
        List<Expenses> expenses = ExpensesRepository.findByUserId(userId);
        return expenses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteExpenseById(Long id) {
        if (!ExpensesRepository.existsById(id)) {
            throw new NoSuchElementException("Expense not found.");
        }
        ExpensesRepository.deleteById(id);
    }


    @Override
    public ResponseEntity<ExpensesDto> updateExpense(Long id, ExpensesDto updatedExpenseDto) {
        Optional<Expenses> optionalExpense = ExpensesRepository.findById(id);

        if (optionalExpense.isPresent()) {
            Expenses existingExpense = optionalExpense.get();

            // Update fields if provided
            if (updatedExpenseDto.getAmount() != 0) existingExpense.setAmount(updatedExpenseDto.getAmount());
            if (updatedExpenseDto.getDescription() != null) existingExpense.setDescription(updatedExpenseDto.getDescription());
            if (updatedExpenseDto.getCategory() != null) existingExpense.setCategory(updatedExpenseDto.getCategory());
            if (updatedExpenseDto.getDate() != null) existingExpense.setDate(updatedExpenseDto.getDate());

            // Save the updated expense
            Expenses updatedExpense = ExpensesRepository.save(existingExpense);
            return ResponseEntity.ok(mapEntityToDto(updatedExpense));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    private ExpensesDto mapEntityToDto(Expenses expense) {
        ExpensesDto dto = new ExpensesDto();
        dto.setAmount(expense.getAmount());
        dto.setDescription(expense.getDescription());
        dto.setCategory(expense.getCategory());
        dto.setUser_id(expense.getUser().getId());
        dto.setBudget_id(expense.getBudget().getId()); // Corrected this line

        return dto;
    }





}






