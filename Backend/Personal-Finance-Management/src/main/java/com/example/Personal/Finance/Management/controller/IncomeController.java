package com.example.Personal.Finance.Management.controller;

import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Service.IncomeService;
import com.example.Personal.Finance.Management.entity.Income;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/income")
public class IncomeController {

    private final IncomeService incomeService;
    private final UserRepository userRepository;

    public IncomeController(IncomeService incomeService, UserRepository userRepository) {
        this.incomeService = incomeService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIncome( @RequestBody Income income) {
        if (income.getUser() == null || income.getUser().getUserid() == 0) {
            return ResponseEntity.badRequest().body("User ID is required to add income!");
        }
        Optional<User> userOptional = userRepository.findById(income.getUser().getUserid());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        }


        income.setUser(userOptional.get());
        Income savedIncome = incomeService.addIncome(income);

        return ResponseEntity.ok(savedIncome);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Income>> getUserIncomes(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeService.getUserIncomes(userId));
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> getTotalIncome(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeService.getTotalIncome(userId));
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long Id, @RequestBody Income updatedIncome) {
        return ResponseEntity.ok(incomeService.updateIncome(Id, updatedIncome));
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long Id) {
        incomeService.deleteIncome(Id);
        return ResponseEntity.ok("Income deleted successfully!");
    }

}
