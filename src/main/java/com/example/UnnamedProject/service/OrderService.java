package com.example.UnnamedProject.service;

import com.example.UnnamedProject.model.Order;
import com.example.UnnamedProject.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService
{
    @Autowired
    private OrderRepo orderRepo;
    public Order save(Order order)
    {
        return orderRepo.save(order);
    }
    public List<Order> findAll()
    {
        return orderRepo.findAll();
    }
    public Order findById(UUID id)
    {
        return orderRepo.findById(id).orElse(null);
    }
    public Order update(Order order)
    {
        if (orderRepo.existsById(order.getId())) return orderRepo.save(order);
        return null;
    }
    public void deleteById(UUID id)
    {
        orderRepo.deleteById(id);
    }
}
