package com.example.UnnamedProject.service;

import com.example.UnnamedProject.model.LogEntry;
import com.example.UnnamedProject.repository.LogEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogEntryService
{
    @Autowired
    private LogEntryRepo logEntryRepo;
    public LogEntry save(LogEntry logEntry)
    {
        return logEntryRepo.save(logEntry);
    }
}
