package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Repository.budgetRepository;
import com.example.Personal.Finance.Management.Service.budgetService;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class budgetServiceImpl implements budgetService {
    private budgetRepository  bugetrepo;

    public budgetServiceImpl(budgetRepository bugetrepo) {
        this.bugetrepo = bugetrepo;
    }

    @Override
    public ResponseEntity<Budget> addBudget(Budget newBudget){
        Budget savedbudget  =  bugetrepo.save((newBudget));
        return ResponseEntity.ok(savedbudget);
    }

    @Override
    public ResponseEntity<Budget> updateBudget(Long id ,Budget updatedbudget){
        Optional<Budget>  existingbudget = bugetrepo.findById(id);
        if(existingbudget.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Budget findbudget = existingbudget.get();
        findbudget.setAmount(updatedbudget.getAmount());
        findbudget.setCategory(updatedbudget.getCategory());
       Budget newBudget = bugetrepo.save(findbudget);
        return ResponseEntity.ok(newBudget);
    }

@Override
    public ResponseEntity<Budget> getBudgetById(Long id){
        Optional<Budget> budget = bugetrepo.findById(id);
        if(budget.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(budget.get());
}

@Override
    public ResponseEntity<Void> deleteBudget(Long id){
        Optional<Budget> existingBudget= bugetrepo.findById(id);
        if(existingBudget.isEmpty()){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bugetrepo.delete(existingBudget.get());
    return ResponseEntity.noContent().build();
}


}
