package com.Project.Store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String orderFullName;
    private String orderFullOrder;
    private String orderContactNumber;
    private String orderStatus;
    private Double orderAmount;
    private int quantity;
    private double totalPrice;
    @OneToOne
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public OrderDetail(String orderFullName, String orderFullOrder, String orderContactNumber,
                       String orderStatus, Double orderAmount, Product product, User user) {
        this.orderFullName = orderFullName;
        this.orderFullOrder = orderFullOrder;
        this.orderContactNumber = orderContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.user = user;
    }
}
