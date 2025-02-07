package com.example.Personal.Finance.Management.Repository;

import com.example.Personal.Finance.Management.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface budgetRepository extends JpaRepository<Budget,Long> {


}
