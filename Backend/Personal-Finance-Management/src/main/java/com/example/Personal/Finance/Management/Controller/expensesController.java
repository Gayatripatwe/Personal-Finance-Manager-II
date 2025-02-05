package com.example.Personal.Finance.Management.Controller;

import com.example.Personal.Finance.Management.Service.expensesService;
import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class expensesController {
    private final expensesService ExpenseService;

    public expensesController(expensesService expenseService) {
        this.ExpenseService = expenseService;
    }
//create expenses
    @PostMapping("/addexpenses")
    public ResponseEntity<Expenses> addExpenses(@RequestBody Expenses E){
    Expenses savedexpense = ExpenseService .addExpenses(E).getBody();
    return ResponseEntity.ok(savedexpense);
    }

    //get expense by id
    @GetMapping("/expense/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable Long id){
        return ExpenseService.getExpenseById(id);
    }

 //get expenses by date
//    @GetMapping("/")


// get expenses by category



    //delete expenses
@DeleteMapping("/deleteexpense/{id}")
public ResponseEntity<Void> deleteExpense(@PathVariable Long id){
        ExpenseService.deleteExpense(id);
        return  ExpenseService.deleteExpense(id);
}


    //edit expenses
    @PutMapping("/Updatexpenses/{id}")
    public ResponseEntity<Expenses> updateExpenses(@PathVariable Long id,@RequestBody Expenses updatedExp){
        ResponseEntity<Expenses> newexpense = ExpenseService.updateExpenses(id,updatedExp);
        return newexpense;
    }


}
