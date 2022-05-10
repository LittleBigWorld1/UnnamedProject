package com.example.UnnamedProject.repository;

import com.example.UnnamedProject.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LogEntryRepo extends JpaRepository<LogEntry, UUID>
{
}
