package com.Project.Store.entity;

import com.Project.Store.DTO.CartDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CartCost {
    private List<CartDto> cartItems;
    private Double totalCost;

    public CartCost(List<CartDto> cartItems, Double totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }

}
