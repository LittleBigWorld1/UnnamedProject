package com.example.UnnamedProject.model.things;

import com.example.UnnamedProject.model.enums.RAMType;
import com.example.UnnamedProject.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ram")
public class RAM {
    @Id
    @GeneratedValue
    @Getter
    private UUID id;
    @Getter @Setter
    private String brand, name;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private RAMType ramType;
    @Getter @Setter
    private int price, volumeGB;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Status status;
}
