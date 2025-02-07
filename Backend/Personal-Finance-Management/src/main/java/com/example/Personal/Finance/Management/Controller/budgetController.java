package com.example.Personal.Finance.Management.Controller;

import com.example.Personal.Finance.Management.Service.budgetService;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/Budget")
public class budgetController {
    private final budgetService Budgetservice;

    public budgetController(budgetService Budgetservice) {
        this.Budgetservice=Budgetservice;
    }

    @PostMapping("/addbudget")
    public ResponseEntity<Budget> addBudget(@RequestBody Budget newbudget){
        Budget savedBudget = Budgetservice.addBudget(newbudget).getBody();
        return ResponseEntity.ok(savedBudget);
    }

   @PutMapping("/updatebudget/{id}")
 public ResponseEntity<Budget> updateBudget( @PathVariable Long id,@RequestBody Budget updatedbudget){
        ResponseEntity<Budget> savedbudget = Budgetservice.updateBudget(id,updatedbudget);
       return savedbudget;
   }

   @GetMapping("/getbudget/{id}")
    public  ResponseEntity<Budget> getBudgetById(@PathVariable Long id){
        ResponseEntity<Budget> budget = Budgetservice.getBudgetById(id);
        return budget;
   }

   @DeleteMapping("/deletebudget/{id}")
    public  ResponseEntity<Void> deleteBudget(@PathVariable Long id){
       return Budgetservice.deleteBudget(id);
   }

}
