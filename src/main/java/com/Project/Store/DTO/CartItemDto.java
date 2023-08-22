package com.Project.Store.DTO;

import com.Project.Store.entity.Cart;
import com.Project.Store.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long id;
    private  Integer quantity;
    private Product product;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productName=" + product.getTitle() +
                '}';
    }


}
