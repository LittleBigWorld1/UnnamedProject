package com.example.UnnamedProject.repository.things;

import com.example.UnnamedProject.model.things.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SmartphoneRepo extends JpaRepository<Smartphone, UUID>
{
}
