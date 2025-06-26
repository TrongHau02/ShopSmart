package com.javaweb.service;

import com.javaweb.domain.Order;
import com.javaweb.domain.OrderDetail;
import com.javaweb.domain.User;
import com.javaweb.repository.OrderDetailRepository;
import com.javaweb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Page<Order> handleGetAllOrder(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public Order fechById(long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        return null;
    }

    public Order handleSaveUpdate(Order order) {
        return this.orderRepository.save(order);
    }

    public void handleDeleteOrderById(long id) {
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            if (order.getOrderDetails() != null) {
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    this.orderDetailRepository.deleteById(orderDetail.getId());
                }
            }
        }
        this.orderRepository.deleteById(id);
    }

    public long handleCountOrder() {
        return this.orderRepository.count();
    }

    public List<Order> fechByUser(User currentUser) {
        return this.orderRepository.findByUser(currentUser);
    }
}
