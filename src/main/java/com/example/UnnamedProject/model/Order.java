package com.example.UnnamedProject.model;

import com.example.UnnamedProject.model.items.LaptopItem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Getter
    private UUID id;
    @ElementCollection
    @Getter @Setter
    private List<UUID> itemIds;
    @Getter @Setter
    private Long date;
}
