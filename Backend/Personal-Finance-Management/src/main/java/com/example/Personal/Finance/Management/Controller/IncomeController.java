package com.example.Personal.Finance.Management.Controller;

import com.example.Personal.Finance.Management.Service.IncomeService;
import com.example.Personal.Finance.Management.entity.Income;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/income")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIncome( @RequestBody Income income) {
        if (income.getUser() == null || income.getUser().getUserid() == 0) {
            return ResponseEntity.badRequest().body("User ID is required to add income!");
        }
        return ResponseEntity.ok(incomeService.addIncome(income));
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
