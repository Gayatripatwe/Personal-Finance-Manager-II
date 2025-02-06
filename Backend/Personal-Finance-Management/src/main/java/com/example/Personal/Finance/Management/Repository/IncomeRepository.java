package com.example.Personal.Finance.Management.Repository;

import com.example.Personal.Finance.Management.entity.Income;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUser(User user);
    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user = :user")
    Optional<Double> sumByUser(@Param("user") User user);
}
