package com.suraj.JWT_App.repository.evaluation;

import com.suraj.JWT_App.entity.evaluation.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}