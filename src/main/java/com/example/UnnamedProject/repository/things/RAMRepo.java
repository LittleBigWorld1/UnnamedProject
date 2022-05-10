package com.example.UnnamedProject.repository.things;

import com.example.UnnamedProject.model.things.RAM;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RAMRepo extends JpaRepository<RAM, UUID>
{
}
