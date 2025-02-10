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

    @PostMapping("/add/{userId}")
    public ResponseEntity<Income> addIncome(@PathVariable Long userId, @RequestBody Income income) {
        return ResponseEntity.ok(incomeService.addIncome(userId, income));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Income>> getUserIncomes(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeService.getUserIncomes(userId));
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> getTotalIncome(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeService.getTotalIncome(userId));
    }
}
