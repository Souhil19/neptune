package com.example.ecommerce.order;

import com.example.ecommerce.customer.CustomerResponse;
import com.example.ecommerce.orderline.OrderLine;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return new Order().builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
