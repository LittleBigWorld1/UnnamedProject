package com.example.UnnamedProject.repository.items;

import com.example.UnnamedProject.model.items.SmartphoneItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SmartphoneItemRepo extends JpaRepository<SmartphoneItem, UUID>
{
}
