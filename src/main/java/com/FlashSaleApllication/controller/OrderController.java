package com.FlashSaleApllication.controller;



import com.FlashSaleApllication.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

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
public ResponseEntity<String> buy(HttpServletRequest request) {

    Long userId = (Long) request.getAttribute("userId");

    if (userId == null) {
        return ResponseEntity.status(401).body("Unauthorized");
    }

    orderService.placeOrder(1L, userId);
    return ResponseEntity.ok("Order placed");
}

}
