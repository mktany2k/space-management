package com.osm.repository;

import com.osm.model.Lot;
import com.osm.model.LotKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, LotKey> {
}