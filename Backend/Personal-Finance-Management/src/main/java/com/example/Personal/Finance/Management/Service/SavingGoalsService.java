package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.entity.SavingGoals;
import java.util.List;
import java.util.Optional;

public interface SavingGoalsService {
     List<SavingGoals> getAllSavingGoals();
     Optional<SavingGoals> getSavingGoalById(Long id);
     List<SavingGoals> getSavingGoalsByUserId(Long userId);
     SavingGoals createSavingGoal(SavingGoals savingGoals);
     void deleteSavingGoal(Long id);
     SavingGoals updateSavingGoal(Long id, SavingGoals updatedGoal);
     SavingGoals addAmountToSavingGoal(Long id, Double amount); // Ensures amount < income
}
