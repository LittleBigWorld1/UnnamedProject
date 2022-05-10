package com.example.UnnamedProject.service.items;

import com.example.UnnamedProject.model.items.LaptopItem;
import com.example.UnnamedProject.model.things.Laptop;
import com.example.UnnamedProject.repository.items.LaptopItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class LaptopItemService
{
    @Autowired
    private LaptopItemRepo laptopItemRepo;

    public LaptopItem save(LaptopItem laptopItem)
    {
        return laptopItemRepo.save(laptopItem);
    }

    public List<LaptopItem> findAll()
    {
        return laptopItemRepo.findAll();
    }

    public LaptopItem findById(UUID id)
    {
        return laptopItemRepo.findById(id).orElse(null);
    }
    public LaptopItem update(LaptopItem laptopItem)
    {
        if (laptopItemRepo.existsById(laptopItem.getId())) return laptopItemRepo.save(laptopItem);
        else return null;
    }
    public boolean existsById(UUID id)
    {
        return laptopItemRepo.existsById(id);
    }
    public void deleteById(UUID id)
    {
        laptopItemRepo.deleteById(id);
    }
}
