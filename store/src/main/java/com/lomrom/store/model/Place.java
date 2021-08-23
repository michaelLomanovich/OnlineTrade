package com.lomrom.store.model;

import com.lomrom.store.enums.PlaceStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "place")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Place extends AbstractEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    private PlaceStatus status;

}
