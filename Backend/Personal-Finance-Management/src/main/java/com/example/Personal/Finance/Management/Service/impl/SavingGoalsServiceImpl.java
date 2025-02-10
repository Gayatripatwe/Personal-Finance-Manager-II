package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Repository.SavingGoalsRepository;
import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Service.SavingGoalsService;
import com.example.Personal.Finance.Management.entity.SavingGoals;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingGoalsServiceImpl implements SavingGoalsService {
    private final SavingGoalsRepository savingGoalsRepository;
    private final UserRepository userRepository;

    public SavingGoalsServiceImpl(SavingGoalsRepository savingGoalsRepository, UserRepository userRepository) {
        this.savingGoalsRepository = savingGoalsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SavingGoals> getAllSavingGoals() {
        return savingGoalsRepository.findAll();
    }

    @Override
    public Optional<SavingGoals> getSavingGoalById(Long id) {
        return savingGoalsRepository.findById(id);
    }

    @Override
    public List<SavingGoals> getSavingGoalsByUserId(Long userId) {
        return savingGoalsRepository.findByUserUserId(userId);
    }

    @Override
    public SavingGoals createSavingGoal(SavingGoals savingGoals) {
        Long userId = savingGoals.getUser().getUserid();
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with id " + userId);
        }
        savingGoals.setUser(optionalUser.get());
        return savingGoalsRepository.save(savingGoals);
    }


    @Override
    public SavingGoals updateSavingGoal(Long id, SavingGoals updatedGoal) {
        SavingGoals goal = savingGoalsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saving Goal not found with id " + id));

        goal.setGoalName(updatedGoal.getGoalName());
        goal.setTargetAmount(updatedGoal.getTargetAmount());
        goal.setCurrentAmount(updatedGoal.getCurrentAmount());
        goal.setDeadline(updatedGoal.getDeadline());

        return savingGoalsRepository.save(goal);
    }

    @Override
    public void deleteSavingGoal(Long id) {
        savingGoalsRepository.deleteById(id);
    }

    @Override
    public SavingGoals addAmountToSavingGoal(Long id, Double amount) {
        SavingGoals goal = savingGoalsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saving Goal not found with id " + id));

        if (amount >= goal.getIncome()) {
            throw new RuntimeException("Cannot add amount. Must be less than the user's income.");
        }

        double newAmount = goal.getCurrentAmount() + amount;
        goal.setCurrentAmount(newAmount);

        return savingGoalsRepository.save(goal);
    }
}
