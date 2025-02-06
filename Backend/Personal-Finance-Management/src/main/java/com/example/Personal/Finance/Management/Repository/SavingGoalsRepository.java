package com.example.Personal.Finance.Management.Repository;

import com.example.Personal.Finance.Management.entity.SavingGoals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingGoalsRepository extends JpaRepository<SavingGoals,Long> {
    List<SavingGoals> findByUser_UserId(Long userId);

}
