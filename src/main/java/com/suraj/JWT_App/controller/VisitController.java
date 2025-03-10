package com.suraj.JWT_App.controller;


import com.suraj.JWT_App.entity.evaluation.Agent;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitRequestDTO;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitResponseDTO;
import com.suraj.JWT_App.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
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

    @GetMapping("/agents/{pinCode}")
    public ResponseEntity<List<Agent>> findAgentsByPinCode(@PathVariable Long pinCode) {
        List<Agent> agentIds = visitService.findAgentsByPinCode(pinCode);
        return new ResponseEntity<>(agentIds, HttpStatus.OK);
    }


    // 📌 CRM Assigns an Agent to the Visit
    @PutMapping("/{visitId}/assign/{agentId}")
    public ResponseEntity<VisitResponseDTO> assignAgent(@PathVariable Long visitId,
                                                        @PathVariable Long agentId,
                                                        @RequestParam (required = false) LocalTime time) {
        VisitResponseDTO visitResponse = visitService.assignAgent(visitId, agentId, time);
        return ResponseEntity.ok(visitResponse);
    }
}
