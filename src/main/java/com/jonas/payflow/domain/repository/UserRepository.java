package com.jonas.payflow.domain.repository;

import com.jonas.payflow.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
