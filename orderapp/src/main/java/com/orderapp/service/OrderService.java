package com.orderapp.service;

import com.orderapp.model.Order;
import com.orderapp.model.Product;
import com.orderapp.repository.OrderRepository;
import com.orderapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findOrders() {
        return repository.findAll();
    }

    public Order findOrderById(Long orderId) {
        Optional<Order> optionalOrder = repository.findById(orderId);
        return optionalOrder.orElse(null);
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> optionalOrder = repository.findById(orderId);

        if (optionalOrder.isPresent()) {
            // Update the existing product with the new data
            Order existingOrder = optionalOrder.get();
            existingOrder.setProductId(updatedOrder.getProductId());
            existingOrder.setQuantity(updatedOrder.getQuantity());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());

            // Save the updated product
            return repository.save(existingOrder);
        } else {
            return null; // Product not found
        }
    }

    public void deleteOrder(Long orderId) {
        repository.deleteById(orderId);
    }
}
