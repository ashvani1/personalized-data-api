package com.niqactivate.personalizedapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopperProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shopperId;

    @OneToMany(mappedBy = "shopperProductList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShelfItem> shelfItems;

}

