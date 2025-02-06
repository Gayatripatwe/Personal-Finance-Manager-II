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

    public Income addIncome(Long userId, Income income) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        income.setUser(user);
        return incomeRepository.save(income);
    }

    public List<Income> getUserIncomes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return incomeRepository.findByUser(user);
    }

    public Double getTotalIncome(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return incomeRepository.sumByUser(user).orElse(0.0);
    }
}
