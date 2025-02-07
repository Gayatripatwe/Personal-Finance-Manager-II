package com.example.Personal.Finance.Management.Repository;

import com.example.Personal.Finance.Management.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface budgetRepository extends JpaRepository<Budget,Long> {
//    List<Budget> findByUserId(Long UserId);

}
