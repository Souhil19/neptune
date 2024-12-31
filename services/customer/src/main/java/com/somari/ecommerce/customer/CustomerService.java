package com.somari.ecommerce.customer;

import com.somari.ecommerce.exception.CostumerNotFoundException;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;


    public String createCustomer(@Valid CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CostumerNotFoundException(String.format("Customer with id %s not found", request.id())));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }

    }


    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existCustomer(String costumerId) {
        return repository.findById(costumerId).isPresent();
    }

    public CustomerResponse findById(String costumerId) {
        var customer = repository.findById(costumerId)
                .orElseThrow(() -> new CostumerNotFoundException(String.format("Customer with id %s not found", costumerId)));
        return mapper.fromCustomer(customer);
    }

    public void deleteCustomer(String costumerId) {
        var customer = repository.findById(costumerId)
                .orElseThrow(() -> new CostumerNotFoundException(String.format("Customer with id %s not found", costumerId)));
        repository.delete(customer);
    }
}
