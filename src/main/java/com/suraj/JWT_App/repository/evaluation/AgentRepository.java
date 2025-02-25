package com.suraj.JWT_App.repository.evaluation;

import com.suraj.JWT_App.entity.evaluation.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}