package com.example.Personal.Finance.Management.Repository;

import com.example.Personal.Finance.Management.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface expensesRepository extends JpaRepository<Expenses,Long> {
    List<Expenses> findByUser_UserId(Long userId);
    Optional<Expenses> findById(Long id);

}
