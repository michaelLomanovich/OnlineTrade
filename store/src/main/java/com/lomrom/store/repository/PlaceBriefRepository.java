package com.lomrom.store.repository;

import com.lomrom.store.model.PlaceBrief;
import com.lomrom.store.model.QPlaceBrief;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
//https://habr.com/ru/post/344450/
public interface PlaceBriefRepository extends ExCustomRepository<PlaceBrief, QPlaceBrief, UUID>{

}
