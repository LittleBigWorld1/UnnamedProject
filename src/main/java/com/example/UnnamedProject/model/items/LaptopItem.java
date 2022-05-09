package com.example.UnnamedProject.model.items;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "laptop_items")
public class LaptopItem
{
    @Id
    @GeneratedValue
    @Getter
    private UUID id;
    @Getter @Setter
    private UUID laptopId;
    @Nullable
    @Getter @Setter
    private UUID ramId,hddId;
    @Getter @Setter
    private int price, warrantyMonths;
}
