package com.example.UnnamedProject.repository.things;

import com.example.UnnamedProject.model.things.SmartphoneCharger;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SmartphoneChargerRepo extends JpaRepository<SmartphoneCharger, UUID>
{
}
