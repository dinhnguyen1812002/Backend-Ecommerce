package com.Project.Store.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderInput {
    private String Name;
    private String Address;
    private String phoneNumber;
    private List<OrderQuantity> orderQuantities ;


}
