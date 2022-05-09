package com.example.UnnamedProject.model.items;

import com.example.UnnamedProject.model.enums.DriveSlotType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "smartphone_items")
public class SmartphoneItem
{
    @Id
    @GeneratedValue
    @Getter
    private UUID id;
    @Getter @Setter
    private UUID smartphoneId;
    @Nullable
    @Getter @Setter
    private UUID chargerId;
    @Getter @Setter
    private int price, warrantyMonths;
}
