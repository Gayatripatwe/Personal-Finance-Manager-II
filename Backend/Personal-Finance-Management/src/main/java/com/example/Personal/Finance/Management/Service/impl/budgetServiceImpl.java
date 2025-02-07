package com.example.Personal.Finance.Management.Service.impl;
import com.example.Personal.Finance.Management.DTO.BudgetDto;
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



    @Override
    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
    }

@Override
    public ResponseEntity<Void> deleteBudget(Long id){
        Optional<Budget> existingBudget= budgetRepository.findById(id);
        if(existingBudget.isEmpty()){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    budgetRepository.delete(existingBudget.get());
    return ResponseEntity.noContent().build();
}

//    @Override
//    public List<Budget> getBudgetsByUserId(Long UserId) {
//        return budgetRepository.findById(UserId);
//    }




}
