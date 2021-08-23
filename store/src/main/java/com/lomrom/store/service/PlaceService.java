package com.lomrom.store.service;

import com.lomrom.store.dto.city.PlaceAddDto;
import com.lomrom.store.dto.city.PlaceGetDto;
import com.lomrom.store.dto.city.PlaceListDto;
import com.lomrom.store.dto.city.PlaceUpdateDto;
import com.lomrom.store.enums.PlaceStatus;
import com.lomrom.store.filter.PlaceFilter;
import com.lomrom.store.model.Place;
import com.lomrom.store.model.QPlaceBrief;
import com.lomrom.store.repository.PlaceBriefRepository;
import com.lomrom.store.repository.PlaceRepository;
import com.lomrom.store.util.PlaceDtoUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class PlaceService implements IPlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceBriefRepository placeBriefRepository;
    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public PlaceService(PlaceRepository placeRepository, PlaceBriefRepository placeBriefRepository, MessageSourceAccessor messageSourceAccessor) {
        this.placeRepository = placeRepository;
        this.placeBriefRepository = placeBriefRepository;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @Override
    public Place createPlace(PlaceAddDto dto) {
        Place place = PlaceDtoUtil.createFromDto(dto);
        return placeRepository.save(place);
    }

    @Override
    public PlaceGetDto getPlace(UUID id) {
        return PlaceDtoUtil.getDto(placeRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSourceAccessor.getMessage("record.not.found"))));
    }

    @Override
    public void updatePlace(UUID id, PlaceUpdateDto dto) {
        Place existingPlace = placeRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSourceAccessor.getMessage("record.not.found")));
        PlaceDtoUtil.updateFromDto(existingPlace, dto);
        placeRepository.save(existingPlace);
    }

    @Override
    public void deletePlace(UUID id) {
        placeRepository.deleteById(id);
    }

    @Override
    public void changeStatus(UUID id, PlaceStatus status) {
        Place existingPlace = placeRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSourceAccessor.getMessage("record.not.found")));
        existingPlace.setStatus(status);
        placeRepository.save(existingPlace);
    }

    @Override
    public Page<PlaceListDto> getPage(PlaceFilter filter, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QPlaceBrief q = QPlaceBrief.placeBrief;

        if(StringUtils.hasText(filter.getName())){
            builder.and(q.name.containsIgnoreCase(filter.getName()));
        }
        if(filter.getStatus()!=null){
            builder.and(q.status.eq(filter.getStatus()));
        }

        return PlaceDtoUtil.toListDto(placeBriefRepository.findAll(builder, pageable));
    }
}
