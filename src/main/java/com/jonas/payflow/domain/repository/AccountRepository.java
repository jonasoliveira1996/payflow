package com.jonas.payflow.domain.repository;

import com.jonas.payflow.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
