package com.suraj.JWT_App.service;

import com.suraj.JWT_App.entity.evaluation.Customer;
import com.suraj.JWT_App.entity.evaluation.Area;
import com.suraj.JWT_App.payload.Evaluation.CustomerDTO;
import com.suraj.JWT_App.repository.evaluation.CustomerRepository;
import com.suraj.JWT_App.repository.evaluation.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AreaRepository areaRepository;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, AreaRepository areaRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.areaRepository = areaRepository;
        this.modelMapper = modelMapper;
    }

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        if (customer.getArea() != null && customer.getArea().getId() != null) {
            Area existingArea = areaRepository.findById(customer.getArea().getId())
                    .orElseThrow(() -> new RuntimeException("Area not found with ID: " + customer.getArea().getId()));
            customer.setArea(existingArea);
        }
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
        if (customerDTO.getName() != null) existingCustomer.setName(customerDTO.getName());
        if (customerDTO.getEmail() != null) existingCustomer.setEmail(customerDTO.getEmail());
        if (customerDTO.getPhone() != null) existingCustomer.setPhone(customerDTO.getPhone());
        if (customerDTO.getAddress() != null) existingCustomer.setAddress(customerDTO.getAddress());
        if (customerDTO.getArea() != null) {
            if (customerDTO.getArea().getId() != null) {
                Area existingArea = areaRepository.findById(customerDTO.getArea().getId())
                        .orElseThrow(() -> new RuntimeException("Area not found with ID: " + customerDTO.getArea().getId()));
                existingCustomer.setArea(existingArea);
            } else {
                existingCustomer.setArea(modelMapper.map(customerDTO.getArea(), Area.class));
            }
        }
        Customer saved = customerRepository.save(existingCustomer);
        return modelMapper.map(saved, CustomerDTO.class);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    public void bookVisit(Long customerId) {
    }
}
