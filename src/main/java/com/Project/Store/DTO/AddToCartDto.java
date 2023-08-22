package com.Project.Store.DTO;

import com.Project.Store.entity.Cart;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartDto {
    private Integer id;
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;

    public AddToCartDto() {
    }
    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }

}
