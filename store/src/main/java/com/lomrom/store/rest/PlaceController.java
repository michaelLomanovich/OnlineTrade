package com.lomrom.store.rest;

import com.lomrom.store.dto.city.PlaceAddDto;
import com.lomrom.store.dto.city.PlaceGetDto;
import com.lomrom.store.dto.city.PlaceListDto;
import com.lomrom.store.enums.PlaceStatus;
import com.lomrom.store.dto.city.PlaceUpdateDto;
import com.lomrom.store.filter.PlaceFilter;
import com.lomrom.store.model.Place;
import com.lomrom.store.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/rest/place")
public class PlaceController {

    private final IPlaceService partyService;

    @Autowired
    public PlaceController(IPlaceService partyService) {
        this.partyService = partyService;
    }

    @PostMapping
    public ResponseEntity<Place> addPlace(@RequestBody PlaceAddDto dto) {
        var created = partyService.createPlace(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceGetDto> getPlace(@PathVariable UUID id) {
        return ResponseEntity.ok(partyService.getPlace(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlace(@PathVariable UUID id, @RequestBody PlaceUpdateDto dto) {
        partyService.updatePlace(id,dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable UUID id) {
        partyService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/status/{id}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable UUID id, PlaceStatus status) {
        partyService.changeStatus(id,status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PlaceListDto>> getPage(@RequestParam PlaceFilter filter, Pageable pageable) {
        return ResponseEntity.ok(partyService.getPage(filter,pageable));
    }
}
