package com.somari.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        service.updateCustomer(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exist/{costumerId}")
    public ResponseEntity<Boolean> existCustomer(@PathVariable String costumerId) {
        return ResponseEntity.ok(service.existCustomer(costumerId));
    }

    @GetMapping("/{costumerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String costumerId) {
        return ResponseEntity.ok(service.findById(costumerId));
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String costumerId) {
        service.deleteCustomer(costumerId);
        return ResponseEntity.ok().build();
    }

}
