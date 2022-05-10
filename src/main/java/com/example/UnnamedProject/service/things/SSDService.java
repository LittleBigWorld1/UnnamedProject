package com.example.UnnamedProject.service.things;

import com.example.UnnamedProject.model.things.SSD;
import com.example.UnnamedProject.model.things.SmartphoneCharger;
import com.example.UnnamedProject.repository.things.SSDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SSDService
{
    @Autowired
    private SSDRepo ssdRepo;
    public SSD findById(UUID id)
    {
        return ssdRepo.findById(id).orElse(null);
    }
    public SSD save(SSD ssd)
    {
        return ssdRepo.save(ssd);
    }
    public List<SSD> findAll()
    {
        return ssdRepo.findAll();
    }
    public SSD update(SSD ssd)
    {
        if (ssdRepo.existsById(ssd.getId())) return ssdRepo.save(ssd);
        return null;
    }
    public void deleteById(UUID id)
    {
        ssdRepo.deleteById(id);
    }
    public boolean exists(UUID id)
    {
        return ssdRepo.existsById(id);
    }
}