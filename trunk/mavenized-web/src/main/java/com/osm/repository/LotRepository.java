package com.osm.repository;

import org.springframework.data.repository.CrudRepository;

import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.enterprise.sql.hbm.LotKey;

public interface LotRepository extends CrudRepository<Lot, LotKey> {
}
