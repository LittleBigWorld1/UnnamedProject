package com.example.UnnamedProject.service.things;

import com.example.UnnamedProject.model.things.HDD;
import com.example.UnnamedProject.repository.things.HDDRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class HDDService
{
    @Autowired
    private HDDRepo hddRepo;
    public HDD findById(UUID id)
    {
        return hddRepo.findById(id).orElse(null);
    }
    public HDD save(HDD hdd)
    {
        return hddRepo.save(hdd);
    }
    public List<HDD> findAll()
    {
        return hddRepo.findAll();
    }
    public HDD update(HDD hdd)
    {
        if (hddRepo.existsById(hdd.getId())) return hddRepo.save(hdd);
        return null;
    }
    public void deleteById(UUID id)
    {
        hddRepo.deleteById(id);
    }
    public boolean exists(UUID id)
    {
        return hddRepo.existsById(id);
    }
}
