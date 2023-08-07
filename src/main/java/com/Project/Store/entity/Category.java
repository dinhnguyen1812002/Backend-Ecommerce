package com.Project.Store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Category name is required")
    @Size(max = 50, message = "Category name must be at most 50 characters")
    private String name;

    private String Description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> listProduct;

    public Category() {
    }

    public Category(Long id, String name, String description, List<Product> listProduct) {
        this.id = id;
        this.name = name;
        Description = description;
        this.listProduct = listProduct;
    }
}
