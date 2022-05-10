package com.example.UnnamedProject.service.things;

import com.example.UnnamedProject.model.things.Laptop;
import com.example.UnnamedProject.model.things.SmartphoneCharger;
import com.example.UnnamedProject.repository.things.SmartphoneChargerRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SmartphoneChargerService
{
    @Autowired
    private SmartphoneChargerRepo smartphoneChargerRepo;
    public SmartphoneCharger findById(UUID id)
    {
        return smartphoneChargerRepo.findById(id).orElse(null);
    }
    public SmartphoneCharger save(SmartphoneCharger smartphoneCharger)
    {
        return smartphoneChargerRepo.save(smartphoneCharger);
    }
    public List<SmartphoneCharger> findAll()
    {
        return smartphoneChargerRepo.findAll();
    }
    public SmartphoneCharger update(SmartphoneCharger smartphoneCharger)
    {
        if (smartphoneChargerRepo.existsById(smartphoneCharger.getId())) return smartphoneChargerRepo.save(smartphoneCharger);
        return null;
    }
    public void deleteById(UUID id)
    {
        smartphoneChargerRepo.deleteById(id);
    }
    public boolean exists(UUID id)
    {
        return smartphoneChargerRepo.existsById(id);
    }
}
