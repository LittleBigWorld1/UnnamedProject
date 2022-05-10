package com.example.UnnamedProject.model;

import com.example.UnnamedProject.model.enums.LogEntryType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Map;
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
    @ElementCollection
    @Getter @Setter
    private Map<String,String> details;
    @Getter @Setter
    private UUID staffId;
    @Getter @Setter
    private Long date;
}
