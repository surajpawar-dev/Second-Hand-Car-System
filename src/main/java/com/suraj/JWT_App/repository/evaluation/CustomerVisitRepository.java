package com.suraj.JWT_App.repository.evaluation;

import com.suraj.JWT_App.entity.evaluation.Customer;
import com.suraj.JWT_App.entity.evaluation.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Long> {
    Optional<CustomerVisit> findByCustomer(Customer customer);
}