package com.lomrom.store.dto.city;

import com.lomrom.store.enums.PlaceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceListDto {
    private UUID id;
    private String name;
    private PlaceStatus status;
}
