package com.lomrom.store.model;

import com.lomrom.store.enums.PlaceStatus;
import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "place")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PlaceBrief extends AbstractEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private PlaceStatus status;

}
