package com.example.Personal.Finance.Management.controller;

import com.example.Personal.Finance.Management.DTO.BudgetDto;
import com.example.Personal.Finance.Management.Service.budgetService;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/Budget")
public class budgetController {
    private final budgetService Budgetservice;

    public budgetController(budgetService Budgetservice) {
        this.Budgetservice=Budgetservice;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBudget(@RequestBody BudgetDto budgetDto) {
        try {
            Budget savedBudget = Budgetservice.addBudget(budgetDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBudget);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/updatebudget/{id}")
    public ResponseEntity<BudgetDto> updateBudget(@PathVariable Long id, @RequestBody BudgetDto updatedBudgetDto) {
        Budget updatedBudget =  Budgetservice.updateBudget(id, updatedBudgetDto);

        // Convert Entity -> DTO before returning response
        BudgetDto responseDto = new BudgetDto(
                updatedBudget.getAmount(),
                updatedBudget.getUserId(),
                updatedBudget.getIncomeId(),
                updatedBudget.getCategory()
        );

        return ResponseEntity.ok(responseDto);
    }



    // Get Budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBudgetById(@PathVariable Long id) {
        try {
            Budget budget = Budgetservice.getBudgetById(id);
            return ResponseEntity.ok(budget);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    // Get Budgets by User ID
//    @GetMapping("/user/{user_id}")
//    public ResponseEntity<?> getBudgetsByUserId(@PathVariable Long user_id) {
//        try {
//            List<Budget> budgets = Budgetservice.getBudgetsByUserId(user_id);
//            return ResponseEntity.ok(budgets);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }

   @DeleteMapping("/deletebudget/{id}")
    public  ResponseEntity<Void> deleteBudget(@PathVariable Long id){
       return Budgetservice.deleteBudget(id);
   }

   //get all budget of one user

}
