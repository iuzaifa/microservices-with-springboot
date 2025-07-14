package com.programingtechie.product_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document("product")
public class Product {


    @Id
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
}
