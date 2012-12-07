package com.osm.repository;


import org.springframework.data.repository.CrudRepository;
import com.osm.model.Lot;
import com.osm.model.LotKey;


public interface LotRepository extends CrudRepository<Lot, LotKey> {
}