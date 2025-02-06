package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Repository.SavingGoalsRepository;
import com.example.Personal.Finance.Management.Service.SavingGoalsService;
import com.example.Personal.Finance.Management.entity.SavingGoals;

import java.util.List;
import java.util.Optional;

public class SavingGoalsServiceimpl implements SavingGoalsService {
    private final SavingGoalsRepository savingGoalsRepository;


    public SavingGoalsServiceimpl(SavingGoalsRepository savingGoalsRepository) {
        this.savingGoalsRepository = savingGoalsRepository;
    }

    public List<SavingGoals> getAllSavingGoals() {
        return savingGoalsRepository.findAll();
    }

    public Optional<SavingGoals> getSavingGoalById(Long id) {
        return savingGoalsRepository.findById(id);
    }

    public List<SavingGoals> getSavingGoalsByUserId(Long userId) {
        return savingGoalsRepository.findByUser_Id(userId);
    }

    public SavingGoals createSavingGoal(SavingGoals savingGoals) {
        return savingGoalsRepository.save(savingGoals);
    }



    @Override
    public SavingGoals updateSavingGoal(Long id, SavingGoals updatedGoal) {
        Optional<SavingGoals> optionalGoal = savingGoalsRepository.findById(id);

        if (optionalGoal.isEmpty()) {
            throw new RuntimeException("Saving Goal not found with id " + id);
        }

        SavingGoals goal = optionalGoal.get();
        goal.setGoalName(updatedGoal.getGoalName());
        goal.setTargetAmount(updatedGoal.getTargetAmount());
        goal.setCurrentAmount(updatedGoal.getCurrentAmount());
        goal.setDeadline(updatedGoal.getDeadline());

        return savingGoalsRepository.save(goal);
    }




    public void deleteSavingGoal(Long id) {
        savingGoalsRepository.deleteById(id);
    }

}
