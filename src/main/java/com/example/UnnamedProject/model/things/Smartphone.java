package com.example.UnnamedProject.model.things;

import com.example.UnnamedProject.model.enums.ChargerPlugType;
import com.example.UnnamedProject.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="smartphones")
public class Smartphone {
    @Id
    @GeneratedValue
    @Getter
    private UUID id;
    @Getter @Setter
    private String brand, name;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private ChargerPlugType chargerPlugType;
    @Getter @Setter
    private int price, chargerVoltage;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Status status;
}
