package com.suraj.JWT_App.controller;

import com.suraj.JWT_App.payload.Evaluation.AgentDTO;
import com.suraj.JWT_App.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    // Create a new agent
    @PostMapping
    public ResponseEntity<AgentDTO> addAgent(@RequestBody AgentDTO agentDTO) {
        return new ResponseEntity<>(agentService.addAgent(agentDTO), HttpStatus.CREATED);
    }

    // Get all agents
    @GetMapping
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    // Get an agent by ID
    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id) {
        return ResponseEntity.ok(agentService.getAgentById(id));
    }

    // Update an agent
    @PutMapping("/{id}")
    public ResponseEntity<AgentDTO> updateAgent(@PathVariable Long id, @RequestBody AgentDTO agentDTO) {
        return ResponseEntity.ok(agentService.updateAgent(id, agentDTO));
    }

    // Delete an agent
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return ResponseEntity.ok("Agent deleted successfully.");
    }
}
