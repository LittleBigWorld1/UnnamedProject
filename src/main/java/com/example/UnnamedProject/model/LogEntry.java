package com.example.UnnamedProject.model;

import com.example.UnnamedProject.model.enums.LogEntryType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "log")
public class LogEntry
{
    @Id
    @GeneratedValue
    @Getter
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private LogEntryType type;
    @ElementCollection
    @Getter @Setter
    private List<UUID> actionIds;
    @Getter @Setter
    private UUID staffId;
}
