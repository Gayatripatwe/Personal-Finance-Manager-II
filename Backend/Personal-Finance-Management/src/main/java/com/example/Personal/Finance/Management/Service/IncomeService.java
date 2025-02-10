package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.entity.Income;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IncomeService {
    public Income addIncome(Income income);
    public List<Income> getUserIncomes(Long userId);
    public Double getTotalIncome(Long userId);
    Income updateIncome(Long Id, Income updatedIncome);
    void deleteIncome(Long Id);
}
