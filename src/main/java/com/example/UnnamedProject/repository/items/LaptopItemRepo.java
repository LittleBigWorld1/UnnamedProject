package com.example.UnnamedProject.repository.items;

import com.example.UnnamedProject.model.items.LaptopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LaptopItemRepo extends JpaRepository<LaptopItem, UUID>
{
}
