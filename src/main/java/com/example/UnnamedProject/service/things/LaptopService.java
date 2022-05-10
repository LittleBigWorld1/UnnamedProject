package com.example.UnnamedProject.service.things;

import com.example.UnnamedProject.model.things.Laptop;
import com.example.UnnamedProject.repository.things.LaptopRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LaptopService
{
    @Autowired
    private LaptopRepo laptopRepo;
    public Laptop findById(UUID id)
    {
        return laptopRepo.findById(id).orElse(null);
    }
    public Laptop save(Laptop laptop)
    {
        return laptopRepo.save(laptop);
    }
    public List<Laptop> findAll()
    {
        return laptopRepo.findAll();
    }
    public Laptop update(Laptop laptop)
    {
        if (laptopRepo.existsById(laptop.getId())) return laptopRepo.save(laptop);
        return null;
    }
    public void deleteById(UUID id)
    {
        laptopRepo.deleteById(id);
    }
    public boolean exists(UUID id)
    {
        return laptopRepo.existsById(id);
    }
}
