package com.example.UnnamedProject.repository;

import com.example.UnnamedProject.model.Order;
import com.example.UnnamedProject.model.items.LaptopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, UUID>
{
}
