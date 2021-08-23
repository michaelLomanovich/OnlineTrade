package com.lomrom.store.repository;

import com.lomrom.store.model.QPlace;
import com.lomrom.store.model.Place;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
//https://habr.com/ru/post/344450/
public interface PlaceRepository extends ExCustomRepository<Place, QPlace, UUID>{

}
