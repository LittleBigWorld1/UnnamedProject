package com.example.UnnamedProject.repository.things;

import com.example.UnnamedProject.model.things.HDD;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HDDRepo extends JpaRepository<HDD, UUID>
{
}
