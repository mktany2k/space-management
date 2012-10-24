package com.osm.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E, ID extends Serializable> {
    E get(ID key);
    
    void create(E entity);
    
    void update(E entity);
    
    void delete(ID key);
    
    List<E> findAll();
}
