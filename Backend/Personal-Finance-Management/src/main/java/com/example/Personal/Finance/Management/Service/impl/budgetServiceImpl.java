package com.example.Personal.Finance.Management.Service.impl;
import com.example.Personal.Finance.Management.DTO.BudgetDto;
import com.example.Personal.Finance.Management.DTO.BudgetResponseDto;
import com.example.Personal.Finance.Management.Repository.IncomeRepository;
import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Repository.budgetRepository;
import com.example.Personal.Finance.Management.Service.budgetService;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Income;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class budgetServiceImpl implements budgetService {
    private final budgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final IncomeRepository incomeRepository;

    public budgetServiceImpl(budgetRepository budgetRepository, UserRepository userRepository, IncomeRepository incomeRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.incomeRepository = incomeRepository;
    }

//Add budget with income id
    @Override
    public Budget addBudget(BudgetDto budgetDto) {
        User user = userRepository.findById(budgetDto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Income income = incomeRepository.findById(budgetDto.getIncome_id())
                .orElseThrow(() -> new RuntimeException("Income not found"));

        if (budgetDto.getAmount() > income.getAmount()) {
            throw new RuntimeException("Budget amount cannot be greater than available income.");
        }

        Budget budget = new Budget();
        budget.setAmount(budgetDto.getAmount());
        budget.setUser(user);
        budget.setIncome(income);
        budget.setCategory(budgetDto.getCategory());

        return budgetRepository.save(budget);
    }

//update budget by userid
    @Override
    public Budget updateBudget(Long id, BudgetDto updatedBudgetDto) {
        return budgetRepository.findById(id)
                .map(existingBudget -> {
                    existingBudget.setAmount(updatedBudgetDto.getAmount());
                    existingBudget.setCategory(updatedBudgetDto.getCategory());
                    existingBudget.setUserId(updatedBudgetDto.getUser_id());  // Ensure this setter exists in Budget
                    existingBudget.setIncomeId(updatedBudgetDto.getIncome_id()); // Ensure this setter exists in Budget

                    return budgetRepository.save(existingBudget);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));
    }


//get budget by  userid
    @Override
    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
    }

//    delete budget by userid
@Override
    public ResponseEntity<Void> deleteBudget(Long id){
        Optional<Budget> existingBudget= budgetRepository.findById(id);
        if(existingBudget.isEmpty()){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    budgetRepository.delete(existingBudget.get());
    return ResponseEntity.noContent().build();
}

//addbudget by userid withour incomeid
@Override
public BudgetResponseDto addBudgetbyuserid(BudgetDto budgetDto) {
    // Find user
    User user = userRepository.findById(budgetDto.getUser_id())
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Calculate total income of the user
    Double totalIncome = incomeRepository.getTotalIncomeByUserId(budgetDto.getUser_id());
    if (totalIncome == null) {
        totalIncome = 0.0; // Default to 0 if user has no income records
    }

    // Validate budget amount
    if (budgetDto.getAmount() > totalIncome) {
        throw new RuntimeException("Budget amount cannot be greater than total available income.");
    }

    // Find any existing income (since Budget has a Many-to-One relationship with Income)
    Income income = incomeRepository.findFirstByUserId(budgetDto.getUser_id())
            .orElse(null); // Set to null if no income exists

    // Create and save budget
    Budget budget = new Budget();
    budget.setAmount(budgetDto.getAmount());
    budget.setUser(user);
    budget.setCategory(budgetDto.getCategory());
    budget.setIncome(income); // Ensure this is explicitly set (can be null)

    Budget savedBudget = budgetRepository.save(budget);

    // Return only the required fields in the response
    return new BudgetResponseDto(savedBudget.getId(), savedBudget.getAmount(), savedBudget.getCategory(), user.getUserid());
}

    @Override
    public List<BudgetResponseDto> getBudgetsByUserId(Long userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);

        return budgets.stream()
                .map(budget -> new BudgetResponseDto(
                        budget.getId(),
                        budget.getAmount(),
                        budget.getCategory(),
                        budget.getUser().getUserid()
                ))
                .collect(Collectors.toList());
    }






}
