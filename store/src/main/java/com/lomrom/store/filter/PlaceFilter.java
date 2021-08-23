package com.lomrom.store.filter;

import com.lomrom.store.enums.PlaceStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlaceFilter {
    private String name;
    private PlaceStatus status;
}
