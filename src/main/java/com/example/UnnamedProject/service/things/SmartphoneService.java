package com.example.UnnamedProject.service.things;

import com.example.UnnamedProject.model.things.Smartphone;
import com.example.UnnamedProject.model.things.Smartphone;
import com.example.UnnamedProject.repository.things.SmartphoneRepo;
import com.example.UnnamedProject.repository.things.SmartphoneRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SmartphoneService
{
    @Autowired
    private SmartphoneRepo smartphoneRepo;
    public Smartphone findById(UUID id)
    {
        return smartphoneRepo.findById(id).orElse(null);
    }
    public Smartphone save(Smartphone smartphone)
    {
        return smartphoneRepo.save(smartphone);
    }
    public List<Smartphone> findAll()
    {
        return smartphoneRepo.findAll();
    }
    public Smartphone update(Smartphone smartphone)
    {
        if (smartphoneRepo.existsById(smartphone.getId())) return smartphoneRepo.save(smartphone);
        return null;
    }
    public void deleteById(UUID id)
    {
        smartphoneRepo.deleteById(id);
    }
    public boolean exists(UUID id)
    {
        return smartphoneRepo.existsById(id);
    }
}
