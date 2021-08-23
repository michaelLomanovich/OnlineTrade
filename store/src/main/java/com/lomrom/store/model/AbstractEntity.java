package com.lomrom.store.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
}
