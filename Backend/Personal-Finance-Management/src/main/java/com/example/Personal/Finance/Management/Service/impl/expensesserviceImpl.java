package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Repository.expensesRepository;
import com.example.Personal.Finance.Management.Service.expensesService;
import com.example.Personal.Finance.Management.entity.Expenses;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class expensesserviceImpl implements expensesService {

    private final expensesRepository exp ;

    public expensesserviceImpl(expensesRepository exp) {
        this.exp = exp;
    }

    @Override
    public ResponseEntity<Expenses> addExpenses(Expenses E){
    Expenses newexp = exp.save(E);
    return ResponseEntity.ok(newexp);
    }

    @Override
    public ResponseEntity<Expenses> updateExpenses(Long id,Expenses updatedExp){
        Optional<Expenses> existingExp = exp.findById(id);
        if(existingExp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Expenses E = existingExp.get();
        E.setAmount(updatedExp.getAmount());
        E.setDiscription(updatedExp.getDiscription());
        E.setDate(updatedExp.getDate());
        E.setCategory(updatedExp.getCategory());

        Expenses newExpenses = exp.save(E);
        return ResponseEntity.ok(newExpenses);
    }

    @Override
    public ResponseEntity<Void> deleteExpense(Long id){
        Optional<Expenses> existingExp =  exp.findById(id);
        if(existingExp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        exp.delete(existingExp.get());
        return ResponseEntity.noContent().build();
    }

@Override
    public ResponseEntity<Expenses> getExpenseById(Long id){
        Optional<Expenses> existingExp  = exp.findById(id);
        if(existingExp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(existingExp.get());
}



}
