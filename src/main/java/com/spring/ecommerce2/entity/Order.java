package com.spring.ecommerce2.entity;

import com.spring.ecommerce2.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    @NotBlank(message = "Order number should not be null")
    private String number;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "shipping_address")
    @NotBlank(message = "Shipping address should not be empty")
    private String shippingAddress;

    @Column(name = "order_amount")
    @NotNull(message = "Order amount should not be null")
    private BigDecimal amount;

    @Column(name = "shipping_fee")
    @NotNull(message = "Shipping fee should not be null")
    private BigDecimal shippingFee;

    @Column(name = "order_total")
    @NotNull(message = "Order total should not be null")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "order_date")
    @NotNull(message = "Order date should not be null")
    private Date orderDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}