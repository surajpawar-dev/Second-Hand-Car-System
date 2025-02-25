package com.suraj.JWT_App.service;

import com.suraj.JWT_App.entity.evaluation.CustomerVisit;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitRequestDTO;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitResponseDTO;
import com.suraj.JWT_App.repository.evaluation.CustomerRepository;
import com.suraj.JWT_App.repository.evaluation.CustomerVisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    private final CustomerVisitRepository visitRepository;
    private final CustomerRepository customerRepository;

    public VisitService(CustomerVisitRepository visitRepository, CustomerRepository customerRepository) {
        this.visitRepository = visitRepository;
        this.customerRepository = customerRepository;
    }

    public VisitResponseDTO bookVisit(VisitRequestDTO visitRequestDTO) {
        CustomerVisit visit = new CustomerVisit();
        customerRepository.findById(visitRequestDTO.getCustomerId())
                .ifPresent(customer -> visit.setCustomer(customer));
        visit.setDate(visitRequestDTO.getPreferredDate());
        visit.setTime(visitRequestDTO.getPreferredTime());
        visit.setStatus("pending");
        CustomerVisit customerVisit = visitRepository.save(visit);
        return convertToResponseDTO(customerVisit);
    }

    public VisitResponseDTO convertToResponseDTO(CustomerVisit visit){
        VisitResponseDTO responseDTO = new VisitResponseDTO();
        responseDTO.setVisitId(visit.getId());
        responseDTO.setCustomerId(visit.getCustomer().getId());
        responseDTO.setPreferredDate(visit.getDate());
        responseDTO.setPreferredTime(visit.getTime());
        responseDTO.setStatus(visit.getStatus());
        return responseDTO;
    }


    public VisitResponseDTO assignAgent(Long visitId, Long agentId) {
        return null;
    }
}
