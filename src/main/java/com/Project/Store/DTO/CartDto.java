package com.Project.Store.DTO;

import com.Project.Store.entity.Cart;
import com.Project.Store.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;

    public CartDto(List<CartItemDto> cartItems, double totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }

    public CartDto() {
    }
}
