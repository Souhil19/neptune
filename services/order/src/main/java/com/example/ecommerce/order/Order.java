package com.example.ecommerce.order;

import com.example.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(STRING)
    private PaymentMethod paymentMethod;
    private String customerId;
    @OneToMany(mappedBy ="order",cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate LastModifiedDate;
}