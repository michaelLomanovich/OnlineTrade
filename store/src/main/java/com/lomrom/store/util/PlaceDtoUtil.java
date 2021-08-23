package com.lomrom.store.util;

import com.lomrom.store.dto.city.PlaceAddDto;
import com.lomrom.store.dto.city.PlaceGetDto;
import com.lomrom.store.dto.city.PlaceListDto;
import com.lomrom.store.dto.city.PlaceUpdateDto;
import com.lomrom.store.enums.PlaceStatus;
import com.lomrom.store.model.Place;
import com.lomrom.store.model.PlaceBrief;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlaceDtoUtil {

    private PlaceDtoUtil() {
    }

    public static Place createFromDto(PlaceAddDto dto) {
        Place place = new Place();
        place.setId(UUID.randomUUID());
        place.setName(dto.getName());
        place.setStatus(PlaceStatus.CREATED);
        return place;
    }

    public static PlaceGetDto getDto(Place place){
        return null; //TODO заполнить поля
    }

    public static void updateFromDto(Place existing, PlaceUpdateDto dto) {
        existing.setName(dto.getName());
    }

    public static Page<PlaceListDto> toListDto(Page<PlaceBrief> briefs) {
        List<PlaceListDto> dtos = briefs.stream().map(placeBrief -> PlaceListDto.builder()
                .id(placeBrief.getId())
                .name(placeBrief.getName())
                .status(placeBrief.getStatus())
                .build()).collect(Collectors.toList());
        return new PageImpl<>(dtos,briefs.getPageable(),briefs.getTotalElements());
    }
}
