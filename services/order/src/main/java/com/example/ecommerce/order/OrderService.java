package com.example.ecommerce.order;

import com.example.ecommerce.customer.CustomerClient;
import com.example.ecommerce.exception.BusinessException;
import com.example.ecommerce.orderline.OrderLine;
import com.example.ecommerce.orderline.OrderLineRequest;
import com.example.ecommerce.orderline.OrderLineService;
import com.example.ecommerce.product.PurchaseRequest;
import com.example.ecommerce.product.productClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final com.example.ecommerce.product.productClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(@Valid OrderRequest request) {
        //check customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create an order :: Customer not found"));


        //purchase products -- from product-service
        this.productClient.purchaseProducts(request.products());

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

        return null;
    }
}
