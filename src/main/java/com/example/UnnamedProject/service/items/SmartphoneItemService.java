package com.example.UnnamedProject.service.items;

import com.example.UnnamedProject.model.items.SmartphoneItem;
import com.example.UnnamedProject.model.things.Smartphone;
import com.example.UnnamedProject.repository.items.SmartphoneItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SmartphoneItemService
{
    @Autowired
    private SmartphoneItemRepo smartphoneItemRepo;
    public SmartphoneItem save(SmartphoneItem smartphoneItem)
    {
        return smartphoneItemRepo.save(smartphoneItem);
    }
    public List<SmartphoneItem> findAll()
    {
        return smartphoneItemRepo.findAll();
    }
    public SmartphoneItem findById(UUID id)
    {
        return smartphoneItemRepo.findById(id).orElse(null);
    }
    public SmartphoneItem update(SmartphoneItem smartphoneItem)
    {
        if (smartphoneItemRepo.existsById(smartphoneItem.getId())) return smartphoneItemRepo.save(smartphoneItem);
        else return null;
    }
    public boolean existsById(UUID id)
    {
        return smartphoneItemRepo.existsById(id);
    }
    public void deleteById(UUID id)
    {
        smartphoneItemRepo.deleteById(id);
    }
}
