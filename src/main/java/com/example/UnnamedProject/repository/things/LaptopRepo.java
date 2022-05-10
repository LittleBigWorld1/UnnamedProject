package com.example.UnnamedProject.repository.things;

import com.example.UnnamedProject.model.things.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LaptopRepo extends JpaRepository<Laptop, UUID>
{
}
