package com.FlashSaleApllication.controller;



import com.FlashSaleApllication.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct() {

        Long productId = 1L; // flash sale product
        Long userId = 100L;  // mock user

        orderService.placeOrder(productId, userId);
        return ResponseEntity.ok("Order placed successfully");
    }
}
