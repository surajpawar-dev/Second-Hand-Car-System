package com.suraj.JWT_App.service;

import com.suraj.JWT_App.Exception.ResourceNotFound;
import com.suraj.JWT_App.entity.evaluation.Agent;
import com.suraj.JWT_App.entity.evaluation.Customer;
import com.suraj.JWT_App.entity.evaluation.CustomerVisit;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitRequestDTO;
import com.suraj.JWT_App.payload.Evaluation.visitDTO.VisitResponseDTO;
import com.suraj.JWT_App.repository.evaluation.AgentRepository;
import com.suraj.JWT_App.repository.evaluation.CustomerRepository;
import com.suraj.JWT_App.repository.evaluation.CustomerVisitRepository;
import com.suraj.JWT_App.service.notificationServices.SmsService;
import com.suraj.JWT_App.service.notificationServices.WhatsAppService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final CustomerVisitRepository visitRepository;
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;
    private final SmsService smsService;
    private final WhatsAppService whatsAppService;

    public VisitService(CustomerVisitRepository visitRepository, CustomerRepository customerRepository, AgentRepository agentRepository, SmsService smsService, WhatsAppService whatsAppService) {
        this.visitRepository = visitRepository;
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.smsService = smsService;
        this.whatsAppService = whatsAppService;
    }

    public VisitResponseDTO bookVisit(VisitRequestDTO visitRequestDTO) {

        Customer customer = customerRepository.findById(visitRequestDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id " + visitRequestDTO.getCustomerId()));

        Optional<CustomerVisit> byCustomer = visitRepository.findByCustomer(customer);
        if (byCustomer.isPresent()){
            throw new RuntimeException("Customer already has a visit scheduled for " + byCustomer.get().getDate());
        }

        CustomerVisit visit = new CustomerVisit();
        visit.setCustomer(customer);
        visit.setDate(visitRequestDTO.getPreferredDate());
        visit.setTime(visitRequestDTO.getPreferredTime());
        visit.setStatus("pending");
        CustomerVisit customerVisit = visitRepository.save(visit);
        VisitResponseDTO visitResponseDTO = convertToResponseDTO(customerVisit);

        // Send SMS notification to the customer
        //  smsService.sendSms(customer.getPhone(), "Your visit is about to booked for " + visit.getDate() + " at " + visit.getTime() + " soon you will get confirmation.");
        smsService.sendSms("+919370303792", "Your visit is about to booked for " + visit.getDate() + " at " + visit.getTime() + " soon you will get confirmation.");
        whatsAppService.sendWhatsAppMessage("+919370303792", "Your visit is about to booked for " + visit.getDate() + " at " + visit.getTime() + " soon you will get confirmation.");

        return visitResponseDTO;
    }

    public VisitResponseDTO convertToResponseDTO(CustomerVisit visit) {
        VisitResponseDTO responseDTO = new VisitResponseDTO();
        responseDTO.setVisitId(visit.getId());
        responseDTO.setCustomerId(visit.getCustomer().getId());
        responseDTO.setPreferredDate(visit.getDate());
        responseDTO.setPreferredTime(visit.getTime());
        responseDTO.setStatus(visit.getStatus());
        if (visit.getAgent() != null)
            responseDTO.setAgentId(visit.getAgent().getId());
        return responseDTO;
    }


    public VisitResponseDTO assignAgent(Long visitId, Long agentId, LocalTime time) {
        CustomerVisit customerVisit = visitRepository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFound("Visit Record Not Found With id " + visitId));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFound("Agent Not Found With id " + agentId));

        if (time != null) {
            customerVisit.setTime(time);
        }

        customerVisit.setStatus("assigned");
        customerVisit.setAgent(agent);
        CustomerVisit updatedVisit = visitRepository.save(customerVisit);

        // If an agent is assigned, send SMS notification to the agent and customer as well
        if (updatedVisit.getAgent() != null) {

            // Send SMS notification to the customer
//            smsService.sendSms(customer.getPhone(), "Your visit is booked for " + visit.getDate() + " at " + visit.getTime());

            smsService.sendSms("+919370303792", "Your visit is booked for " + updatedVisit.getDate() + " at " + updatedVisit.getTime());
            whatsAppService.sendWhatsAppMessage("+919370303792", "Your visit is booked for " + updatedVisit.getDate() + " at " + updatedVisit.getTime());

            smsService.sendSms("+919370303792", "You have been assigned a visit on " + updatedVisit.getDate() + " at " + updatedVisit.getTime());
            whatsAppService.sendWhatsAppMessage("+919370303792", "You have been assigned a visit on " + updatedVisit.getDate() + " at " + updatedVisit.getTime());


//            smsService.sendSms(agent.getPhoneNumber(), "You have been assigned a visit on " + updatedVisit.getDate() + " at " + updatedVisit.getTime());
        }

        return convertToResponseDTO(updatedVisit);
    }

    public List<Agent> findAgentsByPinCode(Long pinCode) {
        return agentRepository.findByArea_PinCode(pinCode);
    }
}
