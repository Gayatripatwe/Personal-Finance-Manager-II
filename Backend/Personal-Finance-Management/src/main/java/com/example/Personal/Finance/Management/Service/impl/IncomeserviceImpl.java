package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Repository.IncomeRepository;
import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Service.IncomeService;
import com.example.Personal.Finance.Management.entity.Income;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeserviceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public IncomeserviceImpl(IncomeRepository incomeRepository, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    public Income addIncome(Income income) {
        if (income.getUser() == null || income.getUser().getUserid() == 0) {
            throw new RuntimeException("User is required to add income!");
        }
        User user = userRepository.findById(income.getUser().getUserid())
                .orElseThrow(() -> new RuntimeException("User not found!" + income.getUser().getUserid()));

        income.setUser(user);
        return incomeRepository.save(income);
    }

    public List<Income> getUserIncomes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!" + userId));

        return incomeRepository.findByUser(user);
    }

    public Double getTotalIncome(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!" + userId));

        return incomeRepository.sumByUser(user).orElse(0.0);
    }

    @Override
    public Income updateIncome(Long Id, Income updatedIncome) {
        Income existingIncome = incomeRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Income not found with ID: " + Id));


        existingIncome.setAmount(updatedIncome.getAmount());
        existingIncome.setDescription(updatedIncome.getDescription());
        existingIncome.setDate(updatedIncome.getDate());

        return incomeRepository.save(existingIncome);
    }

    @Override
    public void deleteIncome(Long Id) {
        Income income = incomeRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Income not found with ID: " + Id));

        incomeRepository.delete(income);
    }
}
