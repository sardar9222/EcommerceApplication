package org.example.service;

import org.example.model.order.Order;
import org.example.model.order.OrderItem;
import org.example.model.order.OrderStatus;
import org.example.model.order.ShoppingCart;
import org.example.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    public void processOrder(Order order) {
        orderRepository.addOrder(order);
        order.updateStatus(OrderStatus.PROCESSING);
        System.out.println("Order " + order.getOrderId() + " is being processed.");
    }

    public Order createOrder(String customerId, ShoppingCart cart) {
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(item -> new OrderItem(item.getProduct(), item.getQuantity()))
                .collect(Collectors.toList());
        String orderId = "O" + (orderRepository.getOrdersByCustomer(customerId).size() + 1);
        return new Order(orderId, customerId, orderItems);
    }

    public List<Order> getCustomerOrders(String customerId) {
        return orderRepository.getOrdersByCustomer(customerId);
    }
}