package com.Project.Store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    private Double price;

    @NotEmpty(message = "Image is required")
    private String image;

    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

}
