package com.suraj.JWT_App.controller;


import com.suraj.JWT_App.payload.Evaluation.AreaDTO;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitRequestDTO;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitResponseDTO;
import com.suraj.JWT_App.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    // 📌 Customer Books a Visit
    @PostMapping("/book-visit")
    public ResponseEntity<VisitResponseDTO> bookVisit(@RequestBody VisitRequestDTO visitRequestDTO) {
        VisitResponseDTO visitResponse = visitService.bookVisit(visitRequestDTO);
        return new ResponseEntity<>(visitResponse, HttpStatus.CREATED);
    }

//    first we have to find the agents by the pincode provoded in visitRequest cusomter

    @GetMapping("/{pinCode}/agents")
    public ResponseEntity<List<AreaDTO>> findAgentsByPinCode(@PathVariable String pinCode) {
        List<AgentResponseDTO> agentResponseList = .findAgentsByPinCode(pinCode);
        return ResponseEntity.ok(agentResponseList);
    }


    // 📌 CRM Assigns an Agent to the Visit
    @PutMapping("/{visitId}/assign/{agentId}")
    public ResponseEntity<VisitResponseDTO> assignAgent(@PathVariable Long visitId, @PathVariable Long agentId) {
        VisitResponseDTO visitResponse = visitService.assignAgent(visitId, agentId);
        return ResponseEntity.ok(visitResponse);
    }
}
