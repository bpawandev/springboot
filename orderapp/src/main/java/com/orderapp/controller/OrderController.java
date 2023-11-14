package com.orderapp.controller;

import com.orderapp.model.Order;
import com.orderapp.model.Product;
import com.orderapp.service.OrderService;
import com.orderapp.service.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllProducts() {
        List<Order> orders = orderService.findOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getProductById(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> createProduct(@RequestBody Order order) {
        Order newOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateProduct(@PathVariable Long orderId, @RequestBody Order order) {
        Order existingOrder = orderService.updateOrder(orderId, order);
        if (existingOrder != null) {
            return ResponseEntity.ok(existingOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

