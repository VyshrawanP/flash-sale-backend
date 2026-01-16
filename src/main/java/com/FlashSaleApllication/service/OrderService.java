package com.FlashSaleApllication.service;


import com.FlashSaleApllication.entity.Order;
import com.FlashSaleApllication.exception.OutOfStockException;
import com.FlashSaleApllication.repository.OrderRepository;
import com.FlashSaleApllication.repository.ProductRepository;
import com.FlashSaleApllication.repository.UserRepository;
import com.FlashSaleApllication.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

@Service
public class OrderService {



    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository,
                        OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void placeOrder(Long productId, Long userId) {

        int updatedRows = productRepository.decrementStock(productId);

        if (updatedRows == 0) {
            throw new OutOfStockException("Product out of stock");
        }

        Order order = new Order();
        order.setProductId(productId);
        order.setUserId(userId);
        order.setStatus("CREATED");


        orderRepository.save(order);
    }
}

