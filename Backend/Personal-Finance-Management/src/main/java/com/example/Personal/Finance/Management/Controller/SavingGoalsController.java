package com.example.Personal.Finance.Management.Controller;


import com.example.Personal.Finance.Management.Service.SavingGoalsService;
import com.example.Personal.Finance.Management.entity.SavingGoals;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/saving-goals")
public class SavingGoalsController {
    private final SavingGoalsService savingGoalsService;

    public SavingGoalsController(SavingGoalsService savingGoalsService) {
        this.savingGoalsService = savingGoalsService;
    }


    @GetMapping
    public ResponseEntity<List<SavingGoals>> getAllSavingGoals() {
        List<SavingGoals> savingGoals = savingGoalsService.getAllSavingGoals();
        return ResponseEntity.ok(savingGoals);
    }

    // Get saving goal by ID
    @GetMapping("/{id}")
    public ResponseEntity<SavingGoals> getSavingGoalById(@PathVariable Long id) {
        Optional<SavingGoals> savingGoal = savingGoalsService.getSavingGoalById(id);
        return savingGoal.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get saving goals by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavingGoals>> getSavingGoalsByUserId(@PathVariable Long userId) {
        List<SavingGoals> savingGoals = savingGoalsService.getSavingGoalsByUserId(userId);
        return ResponseEntity.ok(savingGoals);
    }

    // Create a new saving goal
    @PostMapping
    public ResponseEntity<SavingGoals> createSavingGoal(@RequestBody SavingGoals savingGoals) {
        SavingGoals createdGoal = savingGoalsService.createSavingGoal(savingGoals);
        return ResponseEntity.ok(createdGoal);
    }

    // Update an existing saving goal
    @PutMapping("/{id}")
    public ResponseEntity<SavingGoals> updateSavingGoal(@PathVariable Long id, @RequestBody SavingGoals updatedGoal) {
        try {
            SavingGoals updated = savingGoalsService.updateSavingGoal(id, updatedGoal);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a saving goal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavingGoal(@PathVariable Long id) {
        savingGoalsService.deleteSavingGoal(id);
        return ResponseEntity.noContent().build();
    }
}
