package com.example.ecommerce.order;

import com.example.ecommerce.customer.CustomerClient;
import com.example.ecommerce.exception.BusinessException;
import com.example.ecommerce.kafka.OrderConfirmation;
import com.example.ecommerce.kafka.OrderProducer;
import com.example.ecommerce.orderline.OrderLine;
import com.example.ecommerce.orderline.OrderLineRequest;
import com.example.ecommerce.orderline.OrderLineService;
import com.example.ecommerce.product.PurchaseRequest;
import com.example.ecommerce.product.productClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final com.example.ecommerce.product.productClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(@Valid OrderRequest request) {
        //check customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create an order :: Customer not found"));


        //purchase products -- from product-service
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        // persist order
        var order = this.orderRepository.save(mapper.toOrder(request));
        // persist orderlines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()));
        }
            // todo start payment process

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                       request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
