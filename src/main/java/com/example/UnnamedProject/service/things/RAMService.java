package com.example.UnnamedProject.service.things;

import com.example.UnnamedProject.model.things.Laptop;
import com.example.UnnamedProject.model.things.RAM;
import com.example.UnnamedProject.repository.things.RAMRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class RAMService
{
    @Autowired
    private RAMRepo ramRepo;
    public RAM findById(UUID id)
    {
        return ramRepo.findById(id).orElse(null);
    }
    public RAM save(RAM ram)
    {
        return ramRepo.save(ram);
    }
    public List<RAM> findAll()
    {
        return ramRepo.findAll();
    }
    public RAM update(RAM ram)
    {
        if (ramRepo.existsById(ram.getId())) return ramRepo.save(ram);
        return null;
    }
    public void deleteById(UUID id)
    {
        ramRepo.deleteById(id);
    }
    public boolean exists(UUID id)
    {
        return ramRepo.existsById(id);
    }
}
