package com.FlashSaleApllication.repository;



import com.FlashSaleApllication.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
