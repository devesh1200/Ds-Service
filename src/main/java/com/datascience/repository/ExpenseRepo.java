package com.datascience.repository;

import com.datascience.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ExpenseRepo extends JpaRepository<Expense, Long> {


    List<Expense> findByUserId(String userId);


    List<Expense> findByUserIdAndCreatedAtBetween(String userId, String startDate, String endDate);


    Optional<Expense> findByUserIdAndExternalId(String userId, String externalId);
}
