package com.suraj.JWT_App.service;

import com.suraj.JWT_App.entity.evaluation.Agent;
import com.suraj.JWT_App.entity.evaluation.Area;
import com.suraj.JWT_App.payload.Evaluation.AgentDTO;
import com.suraj.JWT_App.repository.evaluation.AgentRepository;
import com.suraj.JWT_App.repository.evaluation.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final AreaRepository areaRepository;
    private final ModelMapper modelMapper;

    public AgentService(AgentRepository agentRepository, AreaRepository areaRepository, ModelMapper modelMapper) {
        this.agentRepository = agentRepository;
        this.areaRepository = areaRepository;
        this.modelMapper = modelMapper;
    }


    public AgentDTO addAgent(AgentDTO agentDTO) {
        // Map DTO to Entity
        Agent agent = modelMapper.map(agentDTO, Agent.class);

        if (agent.getArea() != null && agent.getArea().getId() != null) {
            // Fetch Area by ID if provided
            Area existingArea = areaRepository.findById(agent.getArea().getId())
                    .orElseThrow(() -> new RuntimeException("Area not found with ID: " + agent.getArea().getId()));
            agent.setArea(existingArea);
        }
        // If no ID is provided, ModelMapper has already created a new Area object
        // and it will be saved automatically due to CascadeType.PERSIST

        // Save the agent (which will also persist a new Area if needed)
        Agent savedAgent = agentRepository.save(agent);
        return modelMapper.map(savedAgent, AgentDTO.class);
    }


    // Get all agents
    public Page<Agent> getAllAgents(int page, int size, String sortBy, String sortOrder) {
        // Sorting and pagination

        Sort.Direction direction = (sortOrder != null && sortOrder.equalsIgnoreCase("DESC"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Agent> paginatedResult = agentRepository.findAll(pageRequest);
        return paginatedResult;


//        return agentRepository.findAll().stream()
//                .map(agent -> modelMapper.map(agent, AgentDTO.class))
//                .collect(Collectors.toList());
    }

    // Get agent by ID
    public AgentDTO getAgentById(Long id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with ID: " + id));
        return modelMapper.map(agent, AgentDTO.class);
    }

    // Update an agent
    public AgentDTO updateAgent(Long id, AgentDTO agentDTO) {
        Agent existingAgent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with ID: " + id));

        // Update only non-null fields
        if (agentDTO.getName() != null) existingAgent.setName(agentDTO.getName());
        if (agentDTO.getEmail() != null) existingAgent.setEmail(agentDTO.getEmail());
        if (agentDTO.getPhone() != null) existingAgent.setPhone(agentDTO.getPhone());

        // Handle Area update
        if (agentDTO.getArea() != null) {
            if (agentDTO.getArea().getId() != null) {
                // Assign existing Area
                Area existingArea = areaRepository.findById(agentDTO.getArea().getId())
                        .orElseThrow(() -> new RuntimeException("Area not found with ID: " + agentDTO.getArea().getId()));
                existingAgent.setArea(existingArea);
            } else {
                // If no ID, let Hibernate persist the new area automatically
                existingAgent.setArea(modelMapper.map(agentDTO.getArea(), Area.class));
            }
        }

        // Save and return updated agent
        Agent saved = agentRepository.save(existingAgent);
        System.out.println(saved.getId());
        return modelMapper.map(saved, AgentDTO.class);
    }


    // Delete an agent
    public void deleteAgent(Long id) {
        if (!agentRepository.existsById(id)) {
            throw new RuntimeException("Agent not found with ID: " + id);
        }
        agentRepository.deleteById(id);
    }
}
