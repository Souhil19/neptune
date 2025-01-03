package com.example.ecommerce.orderline;

import com.example.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return new OrderLine().builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                    new Order().builder()
                        .id(orderLineRequest.orderId())
                        .build()
                )
                .productId(orderLineRequest.productId())

                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
