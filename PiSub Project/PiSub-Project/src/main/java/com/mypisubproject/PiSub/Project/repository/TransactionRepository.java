package com.mypisubproject.PiSub.Project.repository;

import com.mypisubproject.PiSub.Project.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransRef(String transRef);
    Transaction findByAmount(BigDecimal amount);
}
