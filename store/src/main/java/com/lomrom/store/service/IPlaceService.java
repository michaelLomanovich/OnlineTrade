package com.lomrom.store.service;

import com.lomrom.store.dto.city.PlaceAddDto;
import com.lomrom.store.dto.city.PlaceGetDto;
import com.lomrom.store.dto.city.PlaceListDto;
import com.lomrom.store.enums.PlaceStatus;
import com.lomrom.store.dto.city.PlaceUpdateDto;
import com.lomrom.store.filter.PlaceFilter;
import com.lomrom.store.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IPlaceService {

    Place createPlace(PlaceAddDto dto);

    PlaceGetDto getPlace(UUID id);

    void updatePlace(UUID id, PlaceUpdateDto dto);

    void deletePlace(UUID id);

    void changeStatus(UUID id, PlaceStatus status);

    Page<PlaceListDto> getPage(PlaceFilter filter, Pageable pageable);

}
