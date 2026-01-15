package com.FlashSaleApllication.repository;

import com.FlashSaleApllication.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("""
        UPDATE Product p
        SET p.stock = p.stock - 1
        WHERE p.id = :productId AND p.stock > 0
    """)
    int decrementStock(@Param("productId") Long productId);
}
