package com.essoft.JavaSpringPracticum.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="product_name", nullable = false)
    private String productName;

    @Column(name="price", nullable = false)
    private double price;

    @Column(name="expiration_date", nullable = true)
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
}
