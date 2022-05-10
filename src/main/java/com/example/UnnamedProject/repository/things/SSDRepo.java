package com.example.UnnamedProject.repository.things;

import com.example.UnnamedProject.model.things.SSD;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SSDRepo extends JpaRepository<SSD, UUID>
{
}
