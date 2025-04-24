package com.datascience.repository;

import com.datascience.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository

public interface ExpenseRepo extends JpaRepository<Expense, Long> {


    List<Expense> findByUserId(String userId);


    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp startDate, Timestamp endDate);


    Optional<Expense> findByUserIdAndExternalId(String userId, String externalId);
}
